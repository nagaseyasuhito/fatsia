package com.github.nagaseyasuhito.fatsia;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.nagaseyasuhito.fatsia.criteria.Between;
import com.github.nagaseyasuhito.fatsia.criteria.GreaterThan;
import com.github.nagaseyasuhito.fatsia.criteria.In;
import com.github.nagaseyasuhito.fatsia.criteria.LesserEqual;
import com.github.nagaseyasuhito.fatsia.criteria.Or;
import com.github.nagaseyasuhito.fatsia.entity.Group;
import com.github.nagaseyasuhito.fatsia.entity.GroupCriteria;
import com.github.nagaseyasuhito.fatsia.entity.User;
import com.github.nagaseyasuhito.fatsia.entity.UserCriteria;
import com.google.common.collect.ImmutableSortedMap;

public class CriteriasTest {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void before() {
		this.entityManagerFactory = Persistence.createEntityManagerFactory("default");

		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		// group0～group9の生成
		for (int i = 0; i < 10; i++) {
			Group group = new Group();
			group.setName("group" + new DecimalFormat("00").format(i));

			entityManager.persist(group);
		}

		Calendar calendar = Calendar.getInstance();

		// user0～user99の生成
		for (int i = 0; i < 100; i++) {
			User user = new User();
			user.setLoginId("user" + new DecimalFormat("00").format(i));
			user.setPassword("password");
			user.setGroup(entityManager.find(Group.class, (long) i % 10 + 1));

			calendar.set(2011, 11, 1, 0, 0, 0);
			calendar.add(Calendar.DAY_OF_MONTH, i);
			user.setBirthDate(calendar.getTime());

			entityManager.persist(user);
		}

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@After
	public void after() {
		this.entityManagerFactory.close();
	}

	@Test
	public void criteriaTest() {
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		{
			// loginIdがuser50のユーザーを検索
			UserCriteria criteria = new UserCriteria();
			criteria.setLoginId(Criterias.eq("user50"));

			List<User> results = Criterias.find(entityManager, User.class, criteria, ImmutableSortedMap.<String, Boolean> of("id", true));
			assertThat(results.size(), is(1));
		}
		{
			// loginIdがuser10からuser15以外のユーザーを検索(between)
			UserCriteria criteria = new UserCriteria();

			criteria.setLoginId(Criterias.between("user10", "user15").not(true));

			List<User> results = Criterias.find(entityManager, User.class, criteria, ImmutableSortedMap.<String, Boolean> of("id", true));
			assertThat(results.size(), is(94));
		}
		{
			// loginIdがuser50からuser55(between)または、loginIdがuser95より上(greatherThan)または、loginIdがuser05以下(lesserEqual)のユーザーを検索
			UserCriteria criteria = new UserCriteria();

			Between<String> loginIdA = Criterias.between("user50", "user55");
			GreaterThan<String> loginIdB = Criterias.gt("user95");
			LesserEqual<String> loginIdC = Criterias.le("user05");

			Or<String> or = Criterias.or();
			or.add(loginIdA);
			or.add(loginIdB);
			or.add(loginIdC);
			criteria.setLoginId(or);

			List<User> results = Criterias.find(entityManager, User.class, criteria, ImmutableSortedMap.<String, Boolean> of("id", true));
			assertThat(results.size(), is(16));
		}
		{
			// loginIdがuser50からuser55(between)または、loginIdがuser95より上(greatherThan)または、loginIdがuser05以下(lesserEqual)のユーザーを検索
			UserCriteria criteria = new UserCriteria();

			In<Group> group = Criterias.in();
			group.add(entityManager.find(Group.class, 1L));
			criteria.setGroup(group);

			List<User> results = Criterias.find(entityManager, User.class, criteria, ImmutableSortedMap.<String, Boolean> of("id", true));
			assertThat(results.size(), is(10));
		}
		{
			// nameがgroup03のグループに所属するユーザーを検索
			UserCriteria criteria = new UserCriteria();

			GroupCriteria group = new GroupCriteria();
			group.setName(Criterias.eq("group03"));

			criteria.setGroup(group);

			List<User> results = Criterias.find(entityManager, User.class, criteria, ImmutableSortedMap.<String, Boolean> of("id", true));
			assertThat(results.size(), is(10));
		}
		{
			// nameがgroup03または、(loginIdがuser08または、loginIdが%15にマッチする)グループに所属するユーザーの検索
			UserCriteria criteria = new UserCriteria();

			GroupCriteria groupA = new GroupCriteria();
			groupA.setName(Criterias.eq("group03"));

			GroupCriteria groupB = new GroupCriteria();
			Or<User> users = Criterias.or();
			groupB.setUsers(users);
			UserCriteria userA = new UserCriteria();
			userA.setLoginId(Criterias.eq("user08"));
			UserCriteria userB = new UserCriteria();
			userB.setLoginId(Criterias.like("%15"));
			groupB.getUsers().add(userA);
			groupB.getUsers().add(userB);

			Or<Group> group = Criterias.or();
			group.add(groupA);
			group.add(groupB);

			criteria.setGroup(group);

			List<User> results = Criterias.find(entityManager, User.class, criteria, ImmutableSortedMap.<String, Boolean> of("id", true));
			assertThat(results.size(), is(30));
		}
	}
}

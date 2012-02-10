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

import com.github.nagaseyasuhito.fatsia.criteria.Null;
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

		Calendar calendar = Calendar.getInstance();

		// group00〜group90の生成
		for (int i = 0; i < 10; i++) {
			Group parent = new Group();
			parent.setName("group" + new DecimalFormat("00").format(i * 10));

			entityManager.persist(parent);

			// groupX1〜groupX9の生成
			for (int j = 0; j < 9; j++) {
				Group child = new Group();
				child.setName("group" + new DecimalFormat("00").format(i * 10 + j + 1));
				child.setParent(parent);

				entityManager.persist(child);

				for (int k = 0; k < 5; k++) {
					User user = new User();
					user.setLoginId("user" + new DecimalFormat("000").format(i * 100 + j * 10 + k));
					user.setPassword("password");
					user.setGroup(child);

					calendar.set(2011, 11, 1, 0, 0, 0);
					calendar.add(Calendar.DAY_OF_MONTH, i);
					user.setBirthDate(calendar.getTime());

					entityManager.persist(user);
				}
			}
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
			// 全てのユーザーを検索
			UserCriteria criteria = new UserCriteria();

			List<User> results = Criterias.find(entityManager, criteria, ImmutableSortedMap.of("id", true));
			assertThat(results.size(), is(450));
		}
		{
			// loginIdがuser050のユーザーを検索(eq)
			UserCriteria criteria = new UserCriteria();
			criteria.setLoginId(Criterias.eq("user050"));

			List<User> results = Criterias.find(entityManager, criteria, ImmutableSortedMap.of("id", true));
			assertThat(results.size(), is(1));
		}
		{
			// loginIdがuser050以外のユーザーを検索(not)
			UserCriteria criteria = new UserCriteria();
			criteria.setLoginId(Criterias.eq("user050").not());

			List<User> results = Criterias.find(entityManager, criteria, ImmutableSortedMap.of("id", true));
			assertThat(results.size(), is(449));
		}
		{
			// loginIdがuser05%のユーザーを検索(like)
			UserCriteria criteria = new UserCriteria();
			criteria.setLoginId(Criterias.like("user05%"));

			List<User> results = Criterias.find(entityManager, criteria, ImmutableSortedMap.of("id", true));
			assertThat(results.size(), is(5));
		}
		{
			// loginIdがuser050かuser150のユーザーを検索(or)
			UserCriteria criteria = new UserCriteria();
			Or<String> or = Criterias.or();
			or.add(Criterias.eq("user050"));
			or.add(Criterias.eq("user150"));
			criteria.setLoginId(or);

			List<User> results = Criterias.find(entityManager, criteria, ImmutableSortedMap.of("id", true));
			assertThat(results.size(), is(2));
		}
		{
			// loginIdがuser050かuser150のユーザーを検索(or)
			UserCriteria criteria = new UserCriteria();
			Or<String> or = Criterias.or();
			or.add(Criterias.eq("user050"));
			or.add(Criterias.eq("user150"));
			criteria.setLoginId(or);

			List<User> results = Criterias.find(entityManager, criteria, ImmutableSortedMap.of("id", true));
			assertThat(results.size(), is(2));
		}
		{
			// parentがnullのグループを検索(is null)
			GroupCriteria criteria = new GroupCriteria();
			Null<Group> nullable = Criterias.nullable();
			criteria.setParent(nullable);

			List<Group> results = Criterias.find(entityManager, criteria, ImmutableSortedMap.of("id", true));
			assertThat(results.size(), is(10));
		}
		{
			// loginIdがuser050のユーザーのいるグループを検索(join)
			GroupCriteria criteria = new GroupCriteria();
			UserCriteria user = new UserCriteria();
			user.setLoginId(Criterias.eq("user050"));
			criteria.getUsers().add(user);

			List<Group> results = Criterias.find(entityManager, criteria, ImmutableSortedMap.of("id", true));
			assertThat(results.size(), is(1));
		}
	}
}

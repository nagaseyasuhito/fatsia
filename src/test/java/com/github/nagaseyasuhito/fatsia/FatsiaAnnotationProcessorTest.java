package com.github.nagaseyasuhito.fatsia;

import java.io.IOException;
import java.nio.charset.Charset;

import org.seasar.aptina.unit.AptinaTestCase;

import com.github.nagaseyasuhito.fatsia.entity.User;

public class FatsiaAnnotationProcessorTest extends AptinaTestCase {
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		// 対象のクラスのソースパスを指定
		this.addSourcePath("src/test/java");

		// 文字コードの指定
		this.setCharset(Charset.forName("utf-8"));
	}

	public void testAnnotationProcessorToolTest() throws IOException {
		// アノテーションプロセッサを追加
		this.addProcessor(new FatsiaAnnotationProcessor());

		// 対象のクラスを追加
		this.addCompilationUnit(User.class);

		// コンパイル
		this.compile();
	}
}
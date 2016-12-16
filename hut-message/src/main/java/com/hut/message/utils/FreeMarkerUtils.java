package com.hut.message.utils;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
/**
 * @author Jared
 */
public abstract class FreeMarkerUtils {

	public static String parse(String templateName, Object root) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		Configuration cfg = new Configuration();
	
		cfg.setTemplateLoader(new ClassTemplateLoader(FreeMarkerUtils.class, "/"));
		Template tm;
		try {
			tm = cfg.getTemplate(templateName,"utf-8");
			tm.process(root, pw);
			String text = sw.getBuffer().toString();
			return text;
		} catch (IOException | TemplateException e) {
			throw new IllegalArgumentException(e);
		}
		finally{
			IOUtils.closeQuietly(sw);
			IOUtils.closeQuietly(pw);
		}
	}

	public static String parseString(String content, Object root) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		StringTemplateLoader st  = new StringTemplateLoader();
		st.putTemplate("_test", content);
		Configuration cfg = new Configuration();
		cfg.setTemplateLoader(st);
		Template tm;
		try {
			tm = cfg.getTemplate("_test","utf-8");
			tm.process(root, pw);
			String text = sw.getBuffer().toString();
			return text;
		} catch (IOException | TemplateException e) {
			throw new IllegalArgumentException(e);
		}
		finally{
			IOUtils.closeQuietly(sw);
			IOUtils.closeQuietly(pw);
		}
	}
}

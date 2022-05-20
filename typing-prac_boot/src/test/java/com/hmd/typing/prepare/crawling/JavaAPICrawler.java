package com.hmd.typing.prepare.crawling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hmd.typing.modules.clazz.dto.ClassDTO;
import com.hmd.typing.modules.clazz.entity.ClassEntity;
import com.hmd.typing.modules.clazz.repository.ClassRepository;
import com.hmd.typing.modules.module.dto.ModuleDTO;
import com.hmd.typing.modules.module.entity.ModuleEntity;
import com.hmd.typing.modules.module.repository.ModuleRepository;
import com.hmd.typing.modules.packaze.dto.PackageDTO;
import com.hmd.typing.modules.packaze.entity.PackageEntity;
import com.hmd.typing.modules.packaze.repository.PackageRepository;

@SpringBootTest
public class JavaAPICrawler {
	
	@Autowired
	private ModuleRepository moduleRepository;
	@Autowired
	private PackageRepository packageRepository;
	@Autowired
	private ClassRepository classRepository;
	
	@Test
	@DisplayName("모듈 추가")
	public void insertModuleData() {
		ModuleDTO base = new ModuleDTO();
		base.setName("java.base");
		base.setDesc("Defines the foundational APIs of the Java SE Platform.");
		moduleRepository.save(ModuleEntity.create(base));
	}
	
	@Test
	@DisplayName("패키지 추가")
	public void insertPackageData() {
		ModuleEntity base = moduleRepository.findByName("java.base");
		
		PackageDTO lang = new PackageDTO();
		lang.setName("java.lang");
		lang.setDesc("Provides classes that are fundamental to the design of the Java programming language.");

		PackageEntity entity = PackageEntity.create(lang, base);
		packageRepository.save(entity);
	}
	
	@Test
	@DisplayName("클래스 크롤링 및 추가")
	public void insertClassData() {
		
		Document doc;
		List<ClassDTO> classes = new ArrayList<>();
		PackageEntity lang = packageRepository.findByName("java.lang");
		
		try {
			doc = Jsoup.connect("https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/package-summary.html").get();
			Elements classNames  = doc.select("#class-summary .class-summary.col-first");
			Elements classDesc  = doc.select("#class-summary .class-summary.col-last");
			
			for (Element element : classNames) {
				ClassDTO clazz = new ClassDTO();
				clazz.setName(element.text());
				classes.add(clazz);
			}
			
			Iterator<Element> itr = classDesc.iterator();
			
			for (ClassDTO e : classes) {
				e.setDesc(itr.next().text());
				classRepository.save(ClassEntity.create(e, lang));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
}

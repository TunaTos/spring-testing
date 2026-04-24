package springtest.test.archunit;

import com.tngtech.archunit.core.domain.ImportContext;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import jakarta.persistence.Entity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.AbstractDocument;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class ArchUnitTest {

    @Test
    @DisplayName("service의 클래스들은 이름이 반드시 Service로 끝나야한다")
    void test1() {
        JavaClasses classes = new ClassFileImporter().importPackages("springtest.test.archunit");

        ArchRule rules = classes()
                .that().resideInAPackage("..service..")
                .should()
                .haveSimpleNameEndingWith("Service");

        rules.check(classes);
    }


    @Test
    @DisplayName("service 패캐지의 클래스는 반드시 @Service 어노테이션이 있어야한다")
    void test2() {
        JavaClasses classes = new ClassFileImporter().importPackages("springtest.test.archunit");
        ArchRule rule = classes()
                .that().resideInAPackage("..service..")
                .should().beAnnotatedWith(Service.class);


        rule.check(classes);
    }

    @DisplayName("repository 패키지는 service 패키지를 의존하면 안된다")
    @Test
    void test3() {
        JavaClasses classes = new ClassFileImporter().importPackages("springtest.test.archunit");
        ArchRule rule = noClasses()
                .that().resideInAPackage("..repository..")
                .should().dependOnClassesThat().resideInAPackage("..service..");

        rule.check(classes);

    }



    @Test
    @DisplayName("domain 패키지는 service와 repository 패키지를 의존하면 안된다")
    void test4() {
        JavaClasses classes = new ClassFileImporter().importPackages("springtest.test.archunit");
        ArchRule rule = noClasses()
                .that().resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAPackage("..service..")
                .andShould().dependOnClassesThat().resideInAPackage("..repository..");

        rule.check(classes);
    }

    @Test
    @DisplayName("@Entity는 반드시 domain 패키지 안에 있어야한다")
    void test5() {
        JavaClasses classes = new ClassFileImporter().importPackages("springtest.test.archunit");
        ArchRule rule = classes()
                .that().areAnnotatedWith(Entity.class)
                        .should().resideInAPackage("..domain..");

        rule.check(classes);
    }

    @Test
    @DisplayName("이름이 Repository로 끝나는 클래스/인터페이스는 반드시 JpaRepository를 상속해야한다")
    void test6() {
        JavaClasses classes = new ClassFileImporter().importPackages("springtest.test.archunit");

        ArchRule rule = classes()
                .that().haveSimpleNameEndingWith("Repository")
                .should().beAssignableTo(JpaRepository.class);

        rule.check(classes);
    }

    @DisplayName("domain 패키지의 필드는 public이면 안된다")
    @Test
    void test7() {
        JavaClasses classes = new ClassFileImporter().importPackages("springtest.test.archunit");

        ArchRule rule = fields().that().areDeclaredInClassesThat().resideInAPackage("..domain..").should().notBePublic();

        rule.check(classes);
    }

    @DisplayName("service, repository, domain레이어를 선언하고, service -> repositroy -> domain 의존 방향만 허용하는 규칙")
    @Test
    void test8() {
        JavaClasses classes = new ClassFileImporter().importPackages("springtest.test.archunit");

        layeredArchitecture()
                .consideringAllDependencies()
                .layer("Service").definedBy("..service..")
                .layer("Domain").definedBy("..domain..")
                .layer("Repository").definedBy("..repository..")
                .whereLayer("Service").mayNotBeAccessedByAnyLayer()
                .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service")
                .whereLayer("Domain").mayOnlyBeAccessedByLayers("Service", "Repository")
                .check(classes);
    }
}

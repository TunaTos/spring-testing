package springtest.test.junit.test4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import springtest.test.junit.test4.Member;
import springtest.test.junit.test4.MemberService;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberServiceTest {

    static MemberService memberService;
    Member member;

    @BeforeAll
    @DisplayName("beforeAll test")
    static void init() {
        memberService = new MemberService();
    }

    @AfterAll
    @DisplayName("afterAll test")
    static void afterAll() {
        System.out.println("Junit은 재미있어! ");
    }

    @BeforeEach
    @DisplayName("BeforeEach test")
    void beforeEach() {
        System.out.println("claude는 바보");
    }

    @AfterEach
    @DisplayName("afterEach test")
    void afterEach() {
        System.out.println("claude 메롱");
    }

    @Order(1)
    @DisplayName("firstOrder Test")
    @ParameterizedTest
    @CsvSource({"홍길동, hong@test.com"})
    void test1(String name, String email) {
        member = memberService.register(name, email);

        assertAll(
                () -> {assertEquals("홍길동", member.getName());},
                () -> {assertTrue(member.isActive());}
        );
    }

    @Order(2)
    @DisplayName("second order test")
    @ParameterizedTest
    @CsvSource({"'', ''", "'', ''", "'',''"})
    void test2(String name, String email) {
        assertThrows(IllegalArgumentException.class, () -> {
            memberService.register(name, email);
        });
    }

    @Order(3)
    @Test
    @DisplayName("회원 3명 등록 후 전체 수 확인")
    void test3() {
        memberService.register("회원A", "a@test.com");
        memberService.register("회원B", "b@test.com");
        memberService.register("회원C", "c@test.com");

        assertTrue(memberService.count() >= 3);
    }

    @Order(4)
    @Test
    @DisplayName("회원 비활성화 후 active 상태 확인")
    void test4() {
        Member registered = memberService.register("비활성화대상", "deact@test.com");
        Long id = registered.getId();

        memberService.deactivate(id);

        assertFalse(memberService.findById(id).get().isActive());
    }

    @Order(5)
    @Test
    @Disabled("아직 구현 중")
    @DisplayName("활성 회원 수 확인 - 미구현")
    void test5() {
        // TODO: 활성 회원 수 검증 로직 추가 예정
        assertEquals(0, memberService.countActive());
    }

    @Order(6)
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("ValueSource - 유효하지 않은 이름 예외 확인")
    void test6(String invalidName) {
        assertThrows(IllegalArgumentException.class, () ->
                memberService.register(invalidName, "valid@test.com")
        );
    }

}
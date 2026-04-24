package springtest.test.mockito.test2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class LectureServiceTest {

    @Mock
    private MemberService memberService;

    @Mock
    private LectureRepository lectureRepository;

    private LectureService lectureService;

    @BeforeEach
    @DisplayName("lectureService 초기화")
    void init() {
        lectureService = new LectureService(memberService, lectureRepository);
    }

    @ParameterizedTest
    @DisplayName("test1 - any()로 어떤 ID든 동일한 member 반환")
    @ValueSource(longs = {1, 999, 100})
    void test1(Long arg) {
        Member member = new Member(arg, "name", "abcd@naver.com");
        when(memberService.findById(anyLong())).thenReturn(Optional.of(member));

        Optional<Member> result = memberService.findById(arg);
        assertTrue(result.isPresent());
        assertEquals("name", result.get().getName());
    }

    @DisplayName("test2 - createLecture() 정상 동작")
    @ParameterizedTest
    @CsvSource({
            "title1, 1",
            "title2, 2",
            "title3, 3"
    })
    void test2(String title, int limitCount) {
        // anyLong()은 when() 안에서만 사용해야 함 → 일반 객체 생성 시 고정값 사용
        Member member = new Member(1L, "name", "abcd@naver.com");
        Lecture lecture = new Lecture(title, limitCount);

        when(memberService.findById(anyLong())).thenReturn(Optional.of(member));
        // save()는 Lecture를 반환함 → Optional로 감싸면 안 됨
        when(lectureRepository.save(any())).thenReturn(lecture);

        Lecture createdLecture = lectureService.createLecture(member.getId(), lecture);
        assertEquals(member, createdLecture.getOwner());
    }


    @DisplayName("test3")
    @ParameterizedTest
    @CsvSource({
            "title1, 1",
            "title2, 2",
            "title3, 3"
    })
    void test3(String title, int limitCount ) {
        Member member = new Member(1L, "name", "abcd@naver.com");
        Lecture lecture = new Lecture(title, limitCount);
        when(memberService.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            lectureService.createLecture(member.getId(), lecture);
        });
    }

    @DisplayName("findLecture() 없는 강의 조회")
    @Test
    void test4() {
        when(lectureRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () -> {
            lectureService.findLecture(999L);
        });

    }

    @DisplayName("test5 enrollMember() validate실패")
    @Test
    void test5() {
        doThrow(new IllegalArgumentException()).when(memberService).validate(anyLong());
        assertThrows(IllegalArgumentException.class, ()-> {
            lectureService.enrollMember(1L, 999L);
        });
    }

    @DisplayName("test6 complex test")
    @ParameterizedTest
    @CsvSource({
            "스프링 스터디, 10, 홍길동, abc@naver.com",
            "JPA 스터디, 5, 이영희, bbb@nate.net"
    })
    void test6(String title, int limitCount, String name, String email) {
        Lecture lecture = new Lecture(title, limitCount);
        Member member = new Member(1L, name, email);

        when(memberService.findById(anyLong()))
                .thenReturn(Optional.of(member));
        when(lectureRepository.save(any())).thenReturn(lecture);

        Lecture lecture1 = lectureService.createLecture(member.getId(), lecture);
        assertAll(
                () -> {assertEquals(lecture1.getOwner(), member);},
                () -> {assertEquals(title, lecture1.getTitle());}
        );
    }
}
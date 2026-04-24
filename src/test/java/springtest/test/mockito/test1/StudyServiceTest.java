package springtest.test.mockito.test1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;



@ExtendWith(MockitoExtension.class)
public class StudyServiceTest {

    @Mock
    StudyRepository studyRepository;

    @Mock
    MemberService memberService;

    StudyService studyService;

    @BeforeEach
    void setUp() {
        studyService = new StudyService(memberService, studyRepository);
    }

    @Test
    @DisplayName("studyService 생성 test")
    void makeServiceTest() {
        StudyService studyService1 = new StudyService(memberService, studyRepository);
    }

    @Test
    void test1() {
        assertTrue(memberService.findById(1L).isEmpty());
    }
}

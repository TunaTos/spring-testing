package springtest.test.junit.test1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class StudyServiceTest {

    static StudyService studyService;

    @BeforeAll
    static void init() {
        studyService = new StudyService();
    }

    @Test
    @DisplayName("startStudy 호출 후 상태가 STARTED인가")
    void checkStartedAfterStartStudy() {
        Study study = new Study();

        studyService.startStudy(study);
        assertEquals(StudyStatus.STARTED, study.getStatus());
    }

    @Test
    @DisplayName("endStudy 호출 후 상태가 endStudy인가?")
    void checkStartedAfterEndStudy() {
        Study study = new Study();

        studyService.endStudy(study);
        assertEquals(StudyStatus.ENDED, study.getStatus());
    }

    @Test
    @DisplayName("studyService에 Exception이 발생하는가?")
    void checkNullException() {
        assertThrows(IllegalArgumentException.class, () -> {
            studyService.startStudy(null);
        });
    }

}
package springtest.test.junit.test1;

public class StudyService {

    public void startStudy(Study study) {
        if (study == null) {
            throw new IllegalArgumentException("study는 null일 수 없습니다.");
        }
        study.setStatus(StudyStatus.STARTED);
    }

    public void endStudy(Study study) {
        study.setStatus(StudyStatus.ENDED);
    }

}

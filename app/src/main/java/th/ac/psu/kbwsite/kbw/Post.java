package th.ac.psu.kbwsite.kbw;

public class Post {
    String id;
    String examineeID;
    String examineeAccount;
    String moduleNameAbbr;
    String examScheduleID;
    String enterScorePeople;
    String enterScoreDate;
    String isEnterScore;
    String totalScore;
    String examResult;
    String created_at;
    String updated_at;

    public String getId() {
        return id;
    }

    public String getExamineeAccount() {
        return examineeAccount;
    }

    public String getEnterScorePeople() {
        return enterScorePeople;
    }

    public String getEnterScoreDate() {
        return enterScoreDate;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getExamineeID() {
        return examineeID;
    }

    public String getExamScheduleID() {
        return examScheduleID;
    }

    public String getModuleNameAbbr() {
        return moduleNameAbbr;
    }

    public String getExamResult() {
        return examResult;
    }

    public String getIsEnterScore() {
        return isEnterScore;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setEnterScoreDate(String enterScoreDate) {
        this.enterScoreDate = enterScoreDate;
    }

    public void setEnterScorePeople(String enterScorePeople) {
        this.enterScorePeople = enterScorePeople;
    }

    public void setExamineeAccount(String examineeAccount) {
        this.examineeAccount = examineeAccount;
    }

    public void setExamineeID(String examineeID) {
        this.examineeID = examineeID;
    }

    public void setExamScheduleID(String examScheduleID) {
        this.examScheduleID = examScheduleID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIsEnterScore(String isEnterScore) {
        this.isEnterScore = isEnterScore;
    }

    public void setModuleNameAbbr(String moduleNameAbbr) {
        this.moduleNameAbbr = moduleNameAbbr;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setExamResult(String examResult) {
        this.examResult = examResult;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}

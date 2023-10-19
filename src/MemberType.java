public enum MemberType {
    CURRENTMEMBER("Medlem"),
    FORMERMEMBER("Tidigare medlem"),
    NONMEMBER("Ej medlem");
    private final String memberType;

    MemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getMemberType(){
        return memberType;
    }

}
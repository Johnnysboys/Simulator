package Helpers;

public enum Types {
    ERROR("[ERROR]: "),
    WARN("[WARNING]: "),
    INFO("[INFO]: ");

    private final String text;

    private Types(final String text){
        this.text = text;
    }

    @Override
    public String toString(){
        return this.text;
    }
}

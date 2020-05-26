package mvp.tinder.migration;

public class Migrate {
    public static void main(String[] args) {
        DbSetup.clearAndMigrate(true);
    }
}

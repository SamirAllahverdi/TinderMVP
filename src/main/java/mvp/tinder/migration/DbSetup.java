package mvp.tinder.migration;

import mvp.tinder.server.HerokuEnv;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;

public class DbSetup {

  public static void migrate() {
    clearAndMigrate(false);
  }

  public  static void clearAndMigrate(boolean clean) {
    FluentConfiguration config = new FluentConfiguration()
        .dataSource("jdbc:postgresql://ec2-52-7-39-178.compute-1.amazonaws.com:5432/d4iuobfobdm5v4",
                "yvfawzebccozpe",
                "31e99fb3d9f3651c93c1cdecc1d5d4550bf61182ae01dd9cc31994c9019d4035");
//    FluentConfiguration config = new FluentConfiguration()
//            .dataSource(HerokuEnv.jdbc_url(), HerokuEnv.jdbc_username(), HerokuEnv.jdbc_password());
    Flyway flyway = new Flyway(config);
    if (clean) flyway.clean();
    flyway.migrate();
  }

}

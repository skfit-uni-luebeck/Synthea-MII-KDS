package org.example.syntheakds.processing.openehr.generated.kdspersoncomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-11-02T11:32:17.972314700+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.23.0-SNAPSHOT"
)
public class NameVornameElement implements LocatableEntity {
  /**
   * Path: Person/Personendaten/Person/Name/Vorname
   * Description: Der/die identifizierende(n) Vorname(n) der Person innerhalb der Familiengruppe oder durch die sie sozial eindeutig identifiziert wird.
   * Comment: Dieses Datenelement kann in einer Vorlage geklont und umbenannt werden, um eine diskrete Aufzeichnung von „Vorname“, „Zweitname“ und „Spitzname“ oder ähnlichen Arten von Vornamen zu ermöglichen, wie dies für einen bestimmten Anwendungsfall erforderlich ist.
   */
  @Path("/value|value")
  private String value;

  /**
   * Path: Person/Personendaten/Baum/Person/Name/Vorname/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value2;

  /**
   * Path: Person/Personendaten/Person/Name/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(String value) {
     this.value = value;
  }

  public String getValue() {
     return this.value ;
  }

  public void setValue2(NullFlavour value2) {
     this.value2 = value2;
  }

  public NullFlavour getValue2() {
     return this.value2 ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}

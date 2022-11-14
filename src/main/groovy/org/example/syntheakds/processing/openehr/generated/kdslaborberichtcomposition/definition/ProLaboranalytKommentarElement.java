package org.example.syntheakds.processing.openehr.generated.kdslaborberichtcomposition.definition;

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
    date = "2022-11-02T11:29:41.115543800+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.23.0-SNAPSHOT"
)
public class ProLaboranalytKommentarElement implements LocatableEntity {
  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Pro Laboranalyt/Kommentar
   * Description: Kommentar zum Analyt-Ergebnis, soweit noch nicht in anderen Feldern erfasst.
   */
  @Path("/value|value")
  private String value;

  /**
   * Path: Laborbericht/Laborbefund/Event Series/Jedes Ereignis/Tree/Pro Laboranalyt/Kommentar/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value2;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Pro Laboranalyt/feeder_audit
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

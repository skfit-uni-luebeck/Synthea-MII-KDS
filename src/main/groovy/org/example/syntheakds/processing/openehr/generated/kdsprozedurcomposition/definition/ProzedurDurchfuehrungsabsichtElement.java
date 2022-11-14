package org.example.syntheakds.processing.openehr.generated.kdsprozedurcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvCodedText;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-11-02T11:31:51.005543900+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.23.0-SNAPSHOT"
)
public class ProzedurDurchfuehrungsabsichtElement implements LocatableEntity {
  /**
   * Path: KDS_Prozedur/Prozedur/Durchführungsabsicht
   * Description: Grund, warum die angegebene Aktivität für diese Prozedur durchgeführt wurde.
   * Comment: Zum Beispiel: der Grund für den Abbruch oder die Unterbrechung der Prozedur.
   */
  @Path("/value")
  private DvCodedText value;

  /**
   * Path: KDS_Prozedur/Prozedur/Tree/Durchführungsabsicht/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value2;

  /**
   * Path: KDS_Prozedur/Prozedur/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(DvCodedText value) {
     this.value = value;
  }

  public DvCodedText getValue() {
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
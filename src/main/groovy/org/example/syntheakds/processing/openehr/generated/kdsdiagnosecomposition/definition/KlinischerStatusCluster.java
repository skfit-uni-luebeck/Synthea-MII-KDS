package org.example.syntheakds.processing.openehr.generated.kdsdiagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.problem_qualifier.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-11-02T11:31:18.579783100+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.23.0-SNAPSHOT"
)
public class KlinischerStatusCluster implements LocatableEntity {
  /**
   * Path: Diagnose/Primärcode/Klinischer Status/Klinischer Status
   * Description: Eine Kategorie, die die Aufteilung der Problemlisten von Problemen und Diagnosen in aktiv und inaktiv unterstützt.
   * Comment: Die Aktiv/Inaktiv und Aktuell/Vergangen Datenelemente haben einen ähnlichen klinischen Einfluss, repräsentieren aber eine etwas andere Semantik. Beide werden aktiv in verschiedenen klinischen Rahmen benutzt, aber normalerweise nicht zusammen. Wenn ein Aktuell/Vergangen Attribut dokumentiert wird, dann ist dieses Datenelement wahrscheinlich redundant. Eine Ausnahme ist ein Zustand der aktuell, aber inaktiv ist. Ein Beispiel dafür ist Asthma, welches keine akuten Symptome auslöst.
   */
  @Path("/items[at0003 and name/value='Klinischer Status']/value|defining_code")
  private KlinischerStatusDefiningCode klinischerStatusDefiningCode;

  /**
   * Path: Diagnose/Primärcode/Structure/Klinischer Status/Klinischer Status/null_flavour
   */
  @Path("/items[at0003 and name/value='Klinischer Status']/null_flavour|defining_code")
  private NullFlavour klinischerStatusNullFlavourDefiningCode;

  /**
   * Path: Diagnose/Primärcode/Klinischer Status/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setKlinischerStatusDefiningCode(
      KlinischerStatusDefiningCode klinischerStatusDefiningCode) {
     this.klinischerStatusDefiningCode = klinischerStatusDefiningCode;
  }

  public KlinischerStatusDefiningCode getKlinischerStatusDefiningCode() {
     return this.klinischerStatusDefiningCode ;
  }

  public void setKlinischerStatusNullFlavourDefiningCode(
      NullFlavour klinischerStatusNullFlavourDefiningCode) {
     this.klinischerStatusNullFlavourDefiningCode = klinischerStatusNullFlavourDefiningCode;
  }

  public NullFlavour getKlinischerStatusNullFlavourDefiningCode() {
     return this.klinischerStatusNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}

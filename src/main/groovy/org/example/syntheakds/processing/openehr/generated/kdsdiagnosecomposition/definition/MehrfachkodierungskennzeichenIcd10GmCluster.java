package org.example.syntheakds.processing.openehr.generated.kdsdiagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.multiple_coding_icd10gm.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-11-02T11:31:18.561826700+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.23.0-SNAPSHOT"
)
public class MehrfachkodierungskennzeichenIcd10GmCluster implements LocatableEntity {
  /**
   * Path: Diagnose/Primärcode/Mehrfachkodierungskennzeichen_ICD-10-GM/Mehrfachkodierungkennzeichen
   * Description: ICD-10 GM Zusatzkennzeichen nach Kreuz-Stern-System
   */
  @Path("/items[at0001]/value|defining_code")
  private MehrfachkodierungkennzeichenDefiningCode mehrfachkodierungkennzeichenDefiningCode;

  /**
   * Path: Diagnose/Primärcode/Structure/Mehrfachkodierungskennzeichen_ICD-10-GM/Mehrfachkodierungkennzeichen/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour mehrfachkodierungkennzeichenNullFlavourDefiningCode;

  /**
   * Path: Diagnose/Primärcode/Mehrfachkodierungskennzeichen_ICD-10-GM/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setMehrfachkodierungkennzeichenDefiningCode(
      MehrfachkodierungkennzeichenDefiningCode mehrfachkodierungkennzeichenDefiningCode) {
     this.mehrfachkodierungkennzeichenDefiningCode = mehrfachkodierungkennzeichenDefiningCode;
  }

  public MehrfachkodierungkennzeichenDefiningCode getMehrfachkodierungkennzeichenDefiningCode() {
     return this.mehrfachkodierungkennzeichenDefiningCode ;
  }

  public void setMehrfachkodierungkennzeichenNullFlavourDefiningCode(
      NullFlavour mehrfachkodierungkennzeichenNullFlavourDefiningCode) {
     this.mehrfachkodierungkennzeichenNullFlavourDefiningCode = mehrfachkodierungkennzeichenNullFlavourDefiningCode;
  }

  public NullFlavour getMehrfachkodierungkennzeichenNullFlavourDefiningCode() {
     return this.mehrfachkodierungkennzeichenNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}

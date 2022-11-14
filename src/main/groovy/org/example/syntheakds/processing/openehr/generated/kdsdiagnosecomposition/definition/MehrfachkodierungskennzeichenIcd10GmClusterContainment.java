package org.example.syntheakds.processing.openehr.generated.kdsdiagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class MehrfachkodierungskennzeichenIcd10GmClusterContainment extends Containment {
  public SelectAqlField<MehrfachkodierungskennzeichenIcd10GmCluster> MEHRFACHKODIERUNGSKENNZEICHEN_ICD10_GM_CLUSTER = new AqlFieldImp<MehrfachkodierungskennzeichenIcd10GmCluster>(MehrfachkodierungskennzeichenIcd10GmCluster.class, "", "MehrfachkodierungskennzeichenIcd10GmCluster", MehrfachkodierungskennzeichenIcd10GmCluster.class, this);

  public SelectAqlField<MehrfachkodierungkennzeichenDefiningCode> MEHRFACHKODIERUNGKENNZEICHEN_DEFINING_CODE = new AqlFieldImp<MehrfachkodierungkennzeichenDefiningCode>(MehrfachkodierungskennzeichenIcd10GmCluster.class, "/items[at0001]/value|defining_code", "mehrfachkodierungkennzeichenDefiningCode", MehrfachkodierungkennzeichenDefiningCode.class, this);

  public SelectAqlField<NullFlavour> MEHRFACHKODIERUNGKENNZEICHEN_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(MehrfachkodierungskennzeichenIcd10GmCluster.class, "/items[at0001]/null_flavour|defining_code", "mehrfachkodierungkennzeichenNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(MehrfachkodierungskennzeichenIcd10GmCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private MehrfachkodierungskennzeichenIcd10GmClusterContainment() {
    super("openEHR-EHR-CLUSTER.multiple_coding_icd10gm.v1");
  }

  public static MehrfachkodierungskennzeichenIcd10GmClusterContainment getInstance() {
    return new MehrfachkodierungskennzeichenIcd10GmClusterContainment();
  }
}

package org.example.syntheakds.processing.openehr.generated.kdsprozedurcomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.example.syntheakds.processing.openehr.generated.kdsprozedurcomposition.definition.FallidentifikationCluster;
import org.example.syntheakds.processing.openehr.generated.kdsprozedurcomposition.definition.ProzedurAction;

public class KDSProzedurCompositionContainment extends Containment {
  public SelectAqlField<KDSProzedurComposition> K_D_S_PROZEDUR_COMPOSITION = new AqlFieldImp<KDSProzedurComposition>(KDSProzedurComposition.class, "", "KDSProzedurComposition", KDSProzedurComposition.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(KDSProzedurComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

  public SelectAqlField<String> BERICHT_ID_VALUE = new AqlFieldImp<String>(KDSProzedurComposition.class, "/context/other_context[at0001]/items[at0002]/value|value", "berichtIdValue", String.class, this);

  public SelectAqlField<NullFlavour> BERICHT_ID_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(KDSProzedurComposition.class, "/context/other_context[at0001]/items[at0002]/null_flavour|defining_code", "berichtIdNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FallidentifikationCluster> FALLIDENTIFIKATION = new AqlFieldImp<FallidentifikationCluster>(KDSProzedurComposition.class, "/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.case_identification.v0]", "fallidentifikation", FallidentifikationCluster.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(KDSProzedurComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(KDSProzedurComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(KDSProzedurComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(KDSProzedurComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(KDSProzedurComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(KDSProzedurComposition.class, "/context/setting|defining_code", "settingDefiningCode", Setting.class, this);

  public SelectAqlField<ProzedurAction> PROZEDUR = new AqlFieldImp<ProzedurAction>(KDSProzedurComposition.class, "/content[openEHR-EHR-ACTION.procedure.v1]", "prozedur", ProzedurAction.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(KDSProzedurComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(KDSProzedurComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(KDSProzedurComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(KDSProzedurComposition.class, "/territory", "territory", Territory.class, this);

  private KDSProzedurCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.report.v1");
  }

  public static KDSProzedurCompositionContainment getInstance() {
    return new KDSProzedurCompositionContainment();
  }
}

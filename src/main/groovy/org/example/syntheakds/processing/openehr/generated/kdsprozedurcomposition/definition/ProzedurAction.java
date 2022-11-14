package org.example.syntheakds.processing.openehr.generated.kdsprozedurcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.client.classgenerator.shareddefinition.Transition;

@Entity
@Archetype("openEHR-EHR-ACTION.procedure.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-11-02T11:31:50.985827700+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.23.0-SNAPSHOT"
)
public class ProzedurAction implements EntryEntity {
  /**
   * Path: KDS_Prozedur/Prozedur/Name der Prozedur
   * Description: Identifizierung der Prozedur über den Namen.
   * Comment: Wenn möglich wird die Kodierung der spezifischen Prozedur mit einer Terminologie bevorzugt.
   */
  @Path("/description[at0001]/items[at0002 and name/value='Name der Prozedur']/value")
  private DvCodedText nameDerProzedur;

  /**
   * Path: KDS_Prozedur/Prozedur/Tree/Name der Prozedur/null_flavour
   */
  @Path("/description[at0001]/items[at0002 and name/value='Name der Prozedur']/null_flavour|defining_code")
  private NullFlavour nameDerProzedurNullFlavourDefiningCode;

  /**
   * Path: KDS_Prozedur/Prozedur/Freitextbeschreibung
   * Description: Beschreibung der Prozedur, angepasst an den "Pathway"-Verlaufsschritt.
   * Comment: Zum Beispiel: Beschreibung der Durchführung und der Ergebnisse dieser Prozedur, des abgebrochenen Versuchs oder der Stornierung der Prozedur.
   */
  @Path("/description[at0001]/items[at0049 and name/value='Freitextbeschreibung']/value|value")
  private String freitextbeschreibungValue;

  /**
   * Path: KDS_Prozedur/Prozedur/Tree/Freitextbeschreibung/null_flavour
   */
  @Path("/description[at0001]/items[at0049 and name/value='Freitextbeschreibung']/null_flavour|defining_code")
  private NullFlavour freitextbeschreibungNullFlavourDefiningCode;

  /**
   * Path: KDS_Prozedur/Prozedur/Körperstelle
   * Description: Anatomische Lokalisation, an der die Prozedur durchgeführt wird.
   * Comment: Das Vorkommen dieses Datenelements ist nicht eingeschränkt. Dies ermöglicht die Darstellung von klinischen Situationen, in denen alle Eigenschaften, ausgenommen die anatomische Lokalisation, identisch sind, wie z.B. das Entfernen mehrerer Hautläsionen an verschiedenen Stellen. Verwenden Sie dieses Datenelement, um einfache Begriffe oder präkoordinierte anatomische Lokalisationen aufzunehmen. Wenn die Anforderungen an die Erfassung der anatomischen Lokalisation zur Laufzeit durch die Anwendung festgelegt werden oder komplexere Modellierungen wie z.B. die relative Lokalisation erforderlich sind, verwenden Sie entweder CLUSTER.anatomical_location oder CLUSTER.relative_location innerhalb des Slots "Details zur Prozedur" in diesem Archetyp. Wird die anatomische Lokalisation über vordefinierte Codes in den Namen der Prozedur aufgenommen, wird dieses Datenelement redundant.
   */
  @Path("/description[at0001]/items[at0063 and name/value='K\u00f6rperstelle']")
  private List<ProzedurKoerperstelleElement> koerperstelle;

  /**
   * Path: KDS_Prozedur/Prozedur/Seitenlokalisation
   * Description: Anatomische Lokalisation, an der die Prozedur durchgeführt wird.
   * Comment: Das Vorkommen dieses Datenelements ist nicht eingeschränkt. Dies ermöglicht die Darstellung von klinischen Situationen, in denen alle Eigenschaften, ausgenommen die anatomische Lokalisation, identisch sind, wie z.B. das Entfernen mehrerer Hautläsionen an verschiedenen Stellen. Verwenden Sie dieses Datenelement, um einfache Begriffe oder präkoordinierte anatomische Lokalisationen aufzunehmen. Wenn die Anforderungen an die Erfassung der anatomischen Lokalisation zur Laufzeit durch die Anwendung festgelegt werden oder komplexere Modellierungen wie z.B. die relative Lokalisation erforderlich sind, verwenden Sie entweder CLUSTER.anatomical_location oder CLUSTER.relative_location innerhalb des Slots "Details zur Prozedur" in diesem Archetyp. Wird die anatomische Lokalisation über vordefinierte Codes in den Namen der Prozedur aufgenommen, wird dieses Datenelement redundant.
   */
  @Path("/description[at0001]/items[at0063 and name/value='Seitenlokalisation']")
  private List<ProzedurSeitenlokalisationElement> seitenlokalisation;

  /**
   * Path: KDS_Prozedur/Prozedur/Details zur Prozedur
   * Description: Strukturierte Informationen über die Prozedur.
   * Comment: Zur Erfassung detaillierter, strukturierter Informationen über die anatomische Lokalisation, Methode und Technik, verwendetes Equipment, implantierte Geräte, Ergebnisse, Befunde usw.
   */
  @Path("/description[at0001]/items[at0003]")
  private List<Cluster> detailsZurProzedur;

  /**
   * Path: KDS_Prozedur/Prozedur/Multimedia
   * Description: Multimediale Darstellung der durchgeführten Prozedur.
   */
  @Path("/description[at0001]/items[at0062]")
  private List<Cluster> multimedia;

  /**
   * Path: KDS_Prozedur/Prozedur/Kategorie der Prozedur
   * Description: Die Art der Prozedur.
   * Comment: Dieses pragmatische Datenelement kann zur Unterstützung der Gliederung für die Benutzeroberfläche verwendet werden.
   */
  @Path("/description[at0001]/items[at0067 and name/value='Kategorie der Prozedur']/value")
  private DvCodedText kategorieDerProzedur;

  /**
   * Path: KDS_Prozedur/Prozedur/Tree/Kategorie der Prozedur/null_flavour
   */
  @Path("/description[at0001]/items[at0067 and name/value='Kategorie der Prozedur']/null_flavour|defining_code")
  private NullFlavour kategorieDerProzedurNullFlavourDefiningCode;

  /**
   * Path: KDS_Prozedur/Prozedur/Durchführungsabsicht
   * Description: Grund, warum die angegebene Aktivität für diese Prozedur durchgeführt wurde.
   * Comment: Zum Beispiel: der Grund für den Abbruch oder die Unterbrechung der Prozedur.
   */
  @Path("/description[at0001]/items[at0014 and name/value='Durchf\u00fchrungsabsicht']")
  private List<ProzedurDurchfuehrungsabsichtElement> durchfuehrungsabsicht;

  /**
   * Path: KDS_Prozedur/Prozedur/Kommentar
   * Description: Zusätzliche Beschreibung der Aktivität oder der "Pathway"-Verlaufsschritte, die in anderen Bereichen nicht erfasst wurden.
   */
  @Path("/description[at0001]/items[at0005]/value|value")
  private String kommentarValue;

  /**
   * Path: KDS_Prozedur/Prozedur/Tree/Kommentar/null_flavour
   */
  @Path("/description[at0001]/items[at0005]/null_flavour|defining_code")
  private NullFlavour kommentarNullFlavourDefiningCode;

  /**
   * Path: KDS_Prozedur/Prozedur/Antragsteller
   * Description: Angaben über den Gesundheitsdienstleister oder die Organisation, die die Leistung anfordert.
   */
  @Path("/protocol[at0053]/items[at0055]")
  private Cluster antragsteller;

  /**
   * Path: KDS_Prozedur/Prozedur/Empfänger
   * Description: Angaben über den Gesundheitsdienstleister oder die Organisation, die die Leistungsanforderung erhält.
   */
  @Path("/protocol[at0053]/items[at0057]")
  private List<Cluster> empfaenger;

  /**
   * Path: KDS_Prozedur/Prozedur/Erweiterung
   * Description: Zusätzliche Informationen, die erforderlich sind, um lokale Inhalte zu erfassen oder mit anderen Referenzmodellen/Formalismen abzugleichen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR- oder CIMI-Äquivalente.
   */
  @Path("/protocol[at0053]/items[at0064]")
  private List<Cluster> erweiterung;

  /**
   * Path: KDS_Prozedur/Prozedur/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: KDS_Prozedur/Prozedur/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: KDS_Prozedur/Prozedur/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: KDS_Prozedur/Prozedur/time
   */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: KDS_Prozedur/Prozedur/ism_transition/Careflow_step
   */
  @Path("/ism_transition/careflow_step|defining_code")
  private CareflowStepDefiningCode careflowStepDefiningCode;

  /**
   * Path: KDS_Prozedur/Prozedur/ism_transition/Current_state
   */
  @Path("/ism_transition/current_state|defining_code")
  private CurrentStateDefiningCode currentStateDefiningCode;

  /**
   * Path: KDS_Prozedur/Prozedur/ism_transition/transition
   */
  @Path("/ism_transition/transition|defining_code")
  private Transition transitionDefiningCode;

  public void setNameDerProzedur(DvCodedText nameDerProzedur) {
     this.nameDerProzedur = nameDerProzedur;
  }

  public DvCodedText getNameDerProzedur() {
     return this.nameDerProzedur ;
  }

  public void setNameDerProzedurNullFlavourDefiningCode(
      NullFlavour nameDerProzedurNullFlavourDefiningCode) {
     this.nameDerProzedurNullFlavourDefiningCode = nameDerProzedurNullFlavourDefiningCode;
  }

  public NullFlavour getNameDerProzedurNullFlavourDefiningCode() {
     return this.nameDerProzedurNullFlavourDefiningCode ;
  }

  public void setFreitextbeschreibungValue(String freitextbeschreibungValue) {
     this.freitextbeschreibungValue = freitextbeschreibungValue;
  }

  public String getFreitextbeschreibungValue() {
     return this.freitextbeschreibungValue ;
  }

  public void setFreitextbeschreibungNullFlavourDefiningCode(
      NullFlavour freitextbeschreibungNullFlavourDefiningCode) {
     this.freitextbeschreibungNullFlavourDefiningCode = freitextbeschreibungNullFlavourDefiningCode;
  }

  public NullFlavour getFreitextbeschreibungNullFlavourDefiningCode() {
     return this.freitextbeschreibungNullFlavourDefiningCode ;
  }

  public void setKoerperstelle(List<ProzedurKoerperstelleElement> koerperstelle) {
     this.koerperstelle = koerperstelle;
  }

  public List<ProzedurKoerperstelleElement> getKoerperstelle() {
     return this.koerperstelle ;
  }

  public void setSeitenlokalisation(List<ProzedurSeitenlokalisationElement> seitenlokalisation) {
     this.seitenlokalisation = seitenlokalisation;
  }

  public List<ProzedurSeitenlokalisationElement> getSeitenlokalisation() {
     return this.seitenlokalisation ;
  }

  public void setDetailsZurProzedur(List<Cluster> detailsZurProzedur) {
     this.detailsZurProzedur = detailsZurProzedur;
  }

  public List<Cluster> getDetailsZurProzedur() {
     return this.detailsZurProzedur ;
  }

  public void setMultimedia(List<Cluster> multimedia) {
     this.multimedia = multimedia;
  }

  public List<Cluster> getMultimedia() {
     return this.multimedia ;
  }

  public void setKategorieDerProzedur(DvCodedText kategorieDerProzedur) {
     this.kategorieDerProzedur = kategorieDerProzedur;
  }

  public DvCodedText getKategorieDerProzedur() {
     return this.kategorieDerProzedur ;
  }

  public void setKategorieDerProzedurNullFlavourDefiningCode(
      NullFlavour kategorieDerProzedurNullFlavourDefiningCode) {
     this.kategorieDerProzedurNullFlavourDefiningCode = kategorieDerProzedurNullFlavourDefiningCode;
  }

  public NullFlavour getKategorieDerProzedurNullFlavourDefiningCode() {
     return this.kategorieDerProzedurNullFlavourDefiningCode ;
  }

  public void setDurchfuehrungsabsicht(
      List<ProzedurDurchfuehrungsabsichtElement> durchfuehrungsabsicht) {
     this.durchfuehrungsabsicht = durchfuehrungsabsicht;
  }

  public List<ProzedurDurchfuehrungsabsichtElement> getDurchfuehrungsabsicht() {
     return this.durchfuehrungsabsicht ;
  }

  public void setKommentarValue(String kommentarValue) {
     this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
     return this.kommentarValue ;
  }

  public void setKommentarNullFlavourDefiningCode(NullFlavour kommentarNullFlavourDefiningCode) {
     this.kommentarNullFlavourDefiningCode = kommentarNullFlavourDefiningCode;
  }

  public NullFlavour getKommentarNullFlavourDefiningCode() {
     return this.kommentarNullFlavourDefiningCode ;
  }

  public void setAntragsteller(Cluster antragsteller) {
     this.antragsteller = antragsteller;
  }

  public Cluster getAntragsteller() {
     return this.antragsteller ;
  }

  public void setEmpfaenger(List<Cluster> empfaenger) {
     this.empfaenger = empfaenger;
  }

  public List<Cluster> getEmpfaenger() {
     return this.empfaenger ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setCareflowStepDefiningCode(CareflowStepDefiningCode careflowStepDefiningCode) {
     this.careflowStepDefiningCode = careflowStepDefiningCode;
  }

  public CareflowStepDefiningCode getCareflowStepDefiningCode() {
     return this.careflowStepDefiningCode ;
  }

  public void setCurrentStateDefiningCode(CurrentStateDefiningCode currentStateDefiningCode) {
     this.currentStateDefiningCode = currentStateDefiningCode;
  }

  public CurrentStateDefiningCode getCurrentStateDefiningCode() {
     return this.currentStateDefiningCode ;
  }

  public void setTransitionDefiningCode(Transition transitionDefiningCode) {
     this.transitionDefiningCode = transitionDefiningCode;
  }

  public Transition getTransitionDefiningCode() {
     return this.transitionDefiningCode ;
  }
}

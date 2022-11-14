package org.example.syntheakds.processing.openehr.generated.kdspersoncomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvCodedText;
import java.lang.String;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.structured_name.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-11-02T11:32:17.990121400+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.23.0-SNAPSHOT"
)
public class GeburtsnameCluster implements LocatableEntity {
  /**
   * Path: Person/Personendaten/Person/Geburtsname/Namensart
   * Description: Eine ehrenhafte Anrede vor allen Namenskomponenten.
   * Comment: Die Kodierung mit einer externen Terminologie wird nach Möglichkeit bevorzugt. Beispiel: "Doktor", "Ms", "Mx" oder "Professor Dr".
   */
  @Path("/items[at0001 and name/value='Namensart']/value")
  private DvCodedText namensart;

  /**
   * Path: Person/Personendaten/Baum/Person/Geburtsname/Namensart/null_flavour
   */
  @Path("/items[at0001 and name/value='Namensart']/null_flavour|defining_code")
  private NullFlavour namensartNullFlavourDefiningCode;

  /**
   * Path: Person/Personendaten/Person/Geburtsname/Vollständiger Name
   * Description: Der/die identifizierende(n) Vorname(n) der Person innerhalb der Familiengruppe oder durch die sie sozial eindeutig identifiziert wird.
   * Comment: Dieses Datenelement kann in einer Vorlage geklont und umbenannt werden, um eine diskrete Aufzeichnung von „Vorname“, „Zweitname“ und „Spitzname“ oder ähnlichen Arten von Vornamen zu ermöglichen, wie dies für einen bestimmten Anwendungsfall erforderlich ist.
   */
  @Path("/items[at0002 and name/value='Vollst\u00e4ndiger Name']/value|value")
  private String vollstaendigerNameValue;

  /**
   * Path: Person/Personendaten/Baum/Person/Geburtsname/Vollständiger Name/null_flavour
   */
  @Path("/items[at0002 and name/value='Vollst\u00e4ndiger Name']/null_flavour|defining_code")
  private NullFlavour vollstaendigerNameNullFlavourDefiningCode;

  /**
   * Path: Person/Personendaten/Person/Geburtsname/Familienname
   * Description: Der Teil eines Namens, den eine Person normalerweise mit einigen anderen Familienmitgliedern gemeinsam hat, im Unterschied zu ihren eigenen Vornamen.
   * Comment: Auch bekannt als „Familienname“ oder „Zuname“.
   */
  @Path("/items[at0005 and name/value='Familienname']/value|value")
  private String familiennameValue;

  /**
   * Path: Person/Personendaten/Baum/Person/Geburtsname/Familienname/null_flavour
   */
  @Path("/items[at0005 and name/value='Familienname']/null_flavour|defining_code")
  private NullFlavour familiennameNullFlavourDefiningCode;

  /**
   * Path: Person/Personendaten/Person/Geburtsname/Familienname-Namenszusatz
   * Description: Der Teil eines Namens, den eine Person normalerweise mit einigen anderen Familienmitgliedern gemeinsam hat, im Unterschied zu ihren eigenen Vornamen.
   * Comment: Auch bekannt als „Familienname“ oder „Zuname“.
   */
  @Path("/items[at0005 and name/value='Familienname-Namenszusatz']/value|value")
  private String familiennameNamenszusatzValue;

  /**
   * Path: Person/Personendaten/Baum/Person/Geburtsname/Familienname-Namenszusatz/null_flavour
   */
  @Path("/items[at0005 and name/value='Familienname-Namenszusatz']/null_flavour|defining_code")
  private NullFlavour familiennameNamenszusatzNullFlavourDefiningCode;

  /**
   * Path: Person/Personendaten/Person/Geburtsname/Familienname-Nachname
   * Description: Der Teil eines Namens, den eine Person normalerweise mit einigen anderen Familienmitgliedern gemeinsam hat, im Unterschied zu ihren eigenen Vornamen.
   * Comment: Auch bekannt als „Familienname“ oder „Zuname“.
   */
  @Path("/items[at0005 and name/value='Familienname-Nachname']/value|value")
  private String familiennameNachnameValue;

  /**
   * Path: Person/Personendaten/Baum/Person/Geburtsname/Familienname-Nachname/null_flavour
   */
  @Path("/items[at0005 and name/value='Familienname-Nachname']/null_flavour|defining_code")
  private NullFlavour familiennameNachnameNullFlavourDefiningCode;

  /**
   * Path: Person/Personendaten/Person/Geburtsname/Familienname-Vorsatzwort
   * Description: Der Teil eines Namens, den eine Person normalerweise mit einigen anderen Familienmitgliedern gemeinsam hat, im Unterschied zu ihren eigenen Vornamen.
   * Comment: Auch bekannt als „Familienname“ oder „Zuname“.
   */
  @Path("/items[at0005 and name/value='Familienname-Vorsatzwort']/value|value")
  private String familiennameVorsatzwortValue;

  /**
   * Path: Person/Personendaten/Baum/Person/Geburtsname/Familienname-Vorsatzwort/null_flavour
   */
  @Path("/items[at0005 and name/value='Familienname-Vorsatzwort']/null_flavour|defining_code")
  private NullFlavour familiennameVorsatzwortNullFlavourDefiningCode;

  /**
   * Path: Person/Personendaten/Person/Geburtsname/Suffix
   * Description: Ein zusätzlicher Begriff nach allen anderen Namensbestandteilen, in der Regel zur Unterscheidung der Person von einem Familienmitglied mit identischen Namenskomponenten.
   * Comment: Die Codierung mit einer externen Terminologie wird nach Möglichkeit bevorzugt. Zum Beispiel: „Junior (Jr)“, „Senior (Sr)“, " Der Zweite (II)".
   */
  @Path("/items[at0006]")
  private List<GeburtsnameSuffixElement> suffix;

  /**
   * Path: Person/Personendaten/Person/Geburtsname/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setNamensart(DvCodedText namensart) {
     this.namensart = namensart;
  }

  public DvCodedText getNamensart() {
     return this.namensart ;
  }

  public void setNamensartNullFlavourDefiningCode(NullFlavour namensartNullFlavourDefiningCode) {
     this.namensartNullFlavourDefiningCode = namensartNullFlavourDefiningCode;
  }

  public NullFlavour getNamensartNullFlavourDefiningCode() {
     return this.namensartNullFlavourDefiningCode ;
  }

  public void setVollstaendigerNameValue(String vollstaendigerNameValue) {
     this.vollstaendigerNameValue = vollstaendigerNameValue;
  }

  public String getVollstaendigerNameValue() {
     return this.vollstaendigerNameValue ;
  }

  public void setVollstaendigerNameNullFlavourDefiningCode(
      NullFlavour vollstaendigerNameNullFlavourDefiningCode) {
     this.vollstaendigerNameNullFlavourDefiningCode = vollstaendigerNameNullFlavourDefiningCode;
  }

  public NullFlavour getVollstaendigerNameNullFlavourDefiningCode() {
     return this.vollstaendigerNameNullFlavourDefiningCode ;
  }

  public void setFamiliennameValue(String familiennameValue) {
     this.familiennameValue = familiennameValue;
  }

  public String getFamiliennameValue() {
     return this.familiennameValue ;
  }

  public void setFamiliennameNullFlavourDefiningCode(
      NullFlavour familiennameNullFlavourDefiningCode) {
     this.familiennameNullFlavourDefiningCode = familiennameNullFlavourDefiningCode;
  }

  public NullFlavour getFamiliennameNullFlavourDefiningCode() {
     return this.familiennameNullFlavourDefiningCode ;
  }

  public void setFamiliennameNamenszusatzValue(String familiennameNamenszusatzValue) {
     this.familiennameNamenszusatzValue = familiennameNamenszusatzValue;
  }

  public String getFamiliennameNamenszusatzValue() {
     return this.familiennameNamenszusatzValue ;
  }

  public void setFamiliennameNamenszusatzNullFlavourDefiningCode(
      NullFlavour familiennameNamenszusatzNullFlavourDefiningCode) {
     this.familiennameNamenszusatzNullFlavourDefiningCode = familiennameNamenszusatzNullFlavourDefiningCode;
  }

  public NullFlavour getFamiliennameNamenszusatzNullFlavourDefiningCode() {
     return this.familiennameNamenszusatzNullFlavourDefiningCode ;
  }

  public void setFamiliennameNachnameValue(String familiennameNachnameValue) {
     this.familiennameNachnameValue = familiennameNachnameValue;
  }

  public String getFamiliennameNachnameValue() {
     return this.familiennameNachnameValue ;
  }

  public void setFamiliennameNachnameNullFlavourDefiningCode(
      NullFlavour familiennameNachnameNullFlavourDefiningCode) {
     this.familiennameNachnameNullFlavourDefiningCode = familiennameNachnameNullFlavourDefiningCode;
  }

  public NullFlavour getFamiliennameNachnameNullFlavourDefiningCode() {
     return this.familiennameNachnameNullFlavourDefiningCode ;
  }

  public void setFamiliennameVorsatzwortValue(String familiennameVorsatzwortValue) {
     this.familiennameVorsatzwortValue = familiennameVorsatzwortValue;
  }

  public String getFamiliennameVorsatzwortValue() {
     return this.familiennameVorsatzwortValue ;
  }

  public void setFamiliennameVorsatzwortNullFlavourDefiningCode(
      NullFlavour familiennameVorsatzwortNullFlavourDefiningCode) {
     this.familiennameVorsatzwortNullFlavourDefiningCode = familiennameVorsatzwortNullFlavourDefiningCode;
  }

  public NullFlavour getFamiliennameVorsatzwortNullFlavourDefiningCode() {
     return this.familiennameVorsatzwortNullFlavourDefiningCode ;
  }

  public void setSuffix(List<GeburtsnameSuffixElement> suffix) {
     this.suffix = suffix;
  }

  public List<GeburtsnameSuffixElement> getSuffix() {
     return this.suffix ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}

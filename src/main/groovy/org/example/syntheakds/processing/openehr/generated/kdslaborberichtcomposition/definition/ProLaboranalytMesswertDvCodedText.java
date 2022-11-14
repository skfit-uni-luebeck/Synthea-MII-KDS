package org.example.syntheakds.processing.openehr.generated.kdslaborberichtcomposition.definition;

import com.nedap.archie.rm.datavalues.DvCodedText;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-11-02T11:29:41.097312900+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.23.0-SNAPSHOT"
)
@OptionFor("DV_CODED_TEXT")
public class ProLaboranalytMesswertDvCodedText implements RMEntity, ProLaboranalytMesswertChoice {
  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Pro Laboranalyt/Messwert/Messwert
   * Description: (Mess-)Wert des Analyt-Ergebnisses.
   * Comment: Z.B. "7,3 mmol/l", "Erhöht". Der "Any"-Datentyp wird dann durch eine Spezialisierung, eine Vorlage oder zur Laufzeit der Anwendung auf einen passenden Datentyp eingeschränkt werden müssen, um das aktuelle Analyt-Ergebnis wiederzugeben. Der "Quantity"-Datentyp hat Referenzmodell-Attribute, wie Kennungen für normal/abnormal, Referenzbereiche und Näherungen - für weitere Details s. https://specifications.openehr.org/releases/RM/latest/data_types.html#_dv_quantity_class .
   */
  @Path("")
  private DvCodedText messwert;

  public void setMesswert(DvCodedText messwert) {
     this.messwert = messwert;
  }

  public DvCodedText getMesswert() {
     return this.messwert ;
  }
}

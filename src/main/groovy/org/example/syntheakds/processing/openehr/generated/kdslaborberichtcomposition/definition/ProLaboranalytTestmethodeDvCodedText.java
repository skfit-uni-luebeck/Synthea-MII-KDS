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
    date = "2022-11-02T11:29:41.123543600+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.23.0-SNAPSHOT"
)
@OptionFor("DV_CODED_TEXT")
public class ProLaboranalytTestmethodeDvCodedText implements RMEntity, ProLaboranalytTestmethodeChoice {
  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Pro Laboranalyt/Testmethode/Testmethode
   * Description: Die Beschreibung der Methode, mit der der Test nur für diesen Analyten durchgeführt wurde.
   * Comment: Wenn die Testmethode für ein gesamtes Panel gilt, kann die Testmethode mithilfe des Datenelements "Testmethode" im Ergebnis OBSERVATION.laboratory_test_result erfasst werden.
   */
  @Path("")
  private DvCodedText testmethode;

  public void setTestmethode(DvCodedText testmethode) {
     this.testmethode = testmethode;
  }

  public DvCodedText getTestmethode() {
     return this.testmethode ;
  }
}

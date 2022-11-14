package org.example.syntheakds.processing.openehr.generated.kdslaborberichtcomposition.definition;

import java.time.temporal.TemporalAccessor;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-11-02T11:29:41.060357100+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.23.0-SNAPSHOT"
)
@OptionFor("DV_DATE_TIME")
public class ProbenmaterialZeitpunktDerProbenentnahmeDvDateTime implements RMEntity, ProbenmaterialZeitpunktDerProbenentnahmeChoice {
  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Zeitpunkt der Probenentnahme/Zeitpunkt der Probenentnahme
   * Description: Das Datum und die Uhrzeit, zu der die Probennahme angeordnet wurde oder stattfand.
   * Comment: Dieser Zeitpunkt wird hauptsächlich in den Zeitangaben, von INSTRUCTION, ACTION oder OBSERVATION erfasst. Da es sich jedoch um eine wichtige Information handelt, kann es nützlich sein, diese auch direkt mit der Probe selbst zu verknüpfen.
   */
  @Path("|value")
  private TemporalAccessor zeitpunktDerProbenentnahmeValue;

  public void setZeitpunktDerProbenentnahmeValue(TemporalAccessor zeitpunktDerProbenentnahmeValue) {
     this.zeitpunktDerProbenentnahmeValue = zeitpunktDerProbenentnahmeValue;
  }

  public TemporalAccessor getZeitpunktDerProbenentnahmeValue() {
     return this.zeitpunktDerProbenentnahmeValue ;
  }
}

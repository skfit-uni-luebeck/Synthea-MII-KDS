package org.example.syntheakds.processing.openehr.generated.kdslaborberichtcomposition.definition;

import java.net.URI;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-11-02T11:29:41.119546400+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.23.0-SNAPSHOT"
)
@OptionFor("DV_URI")
public class ProLaboranalytProbeIdDvUri implements RMEntity, ProLaboranalytProbeIdChoice {
  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Pro Laboranalyt/Probe ID/Probe ID
   * Description: Kennung der Probe, die für das Analyseergebnis verwendet wurde.
   * Comment: In manchen Situationen wird ein einzelner Laborergebnis-Archetyp mehrere Probe- und Laboranalyt-Ergebnis-Archetypen enthalten. In diesen Fällen wird dieses "Probe"-Datenelement benötigt, um die Ergebnisse mit den richtigen Proben zu verknüpfen.
   */
  @Path("|value")
  private URI probeIdValue;

  public void setProbeIdValue(URI probeIdValue) {
     this.probeIdValue = probeIdValue;
  }

  public URI getProbeIdValue() {
     return this.probeIdValue ;
  }
}

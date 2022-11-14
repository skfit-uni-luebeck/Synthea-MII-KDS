package org.example.syntheakds.processing.openehr.generated.kdslaborberichtcomposition.definition;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-11-02T11:29:41.118541500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.23.0-SNAPSHOT"
)
@OptionFor("DV_IDENTIFIER")
public class ProLaboranalytProbeIdDvIdentifier implements RMEntity, ProLaboranalytProbeIdChoice {
  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Pro Laboranalyt/Probe ID/Probe ID
   * Description: Kennung der Probe, die für das Analyseergebnis verwendet wurde.
   * Comment: In manchen Situationen wird ein einzelner Laborergebnis-Archetyp mehrere Probe- und Laboranalyt-Ergebnis-Archetypen enthalten. In diesen Fällen wird dieses "Probe"-Datenelement benötigt, um die Ergebnisse mit den richtigen Proben zu verknüpfen.
   */
  @Path("")
  private DvIdentifier probeId;

  public void setProbeId(DvIdentifier probeId) {
     this.probeId = probeId;
  }

  public DvIdentifier getProbeId() {
     return this.probeId ;
  }
}

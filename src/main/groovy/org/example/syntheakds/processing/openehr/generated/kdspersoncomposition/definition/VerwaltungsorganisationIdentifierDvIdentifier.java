package org.example.syntheakds.processing.openehr.generated.kdspersoncomposition.definition;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-11-02T11:32:18.064399300+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.23.0-SNAPSHOT"
)
@OptionFor("DV_IDENTIFIER")
public class VerwaltungsorganisationIdentifierDvIdentifier implements RMEntity, VerwaltungsorganisationIdentifierChoice {
  /**
   * Path: Person/Personendaten/Person/Verwaltungsorganisation/Identifier/Identifier
   * Description: Identifikator, der der identifizierten Organisation zugeordnet ist.
   * Comment: Kardinalität für dieses Datenelement wird auf 0..* gesetzt, damit mehr als ein Identifikator aufgezeichnet werden kann. Beachten Sie, dass der ID-Datentyp mehrere Unterkomponenten zum Aufzeichnen des ID-Werts, -Typs, -Ausstellers und -Zuweisers enthält.
   */
  @Path("")
  private DvIdentifier identifier;

  public void setIdentifier(DvIdentifier identifier) {
     this.identifier = identifier;
  }

  public DvIdentifier getIdentifier() {
     return this.identifier ;
  }
}

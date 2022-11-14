package org.example.syntheakds.processing.openehr.generated.kdspersoncomposition.definition;

import java.lang.String;
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
@OptionFor("DV_TEXT")
public class VerwaltungsorganisationIdentifierDvText implements RMEntity, VerwaltungsorganisationIdentifierChoice {
  /**
   * Path: Person/Personendaten/Person/Verwaltungsorganisation/Identifier/Identifier
   * Description: Identifikator, der der identifizierten Organisation zugeordnet ist.
   * Comment: Kardinalität für dieses Datenelement wird auf 0..* gesetzt, damit mehr als ein Identifikator aufgezeichnet werden kann. Beachten Sie, dass der ID-Datentyp mehrere Unterkomponenten zum Aufzeichnen des ID-Werts, -Typs, -Ausstellers und -Zuweisers enthält.
   */
  @Path("|value")
  private String identifierValue;

  public void setIdentifierValue(String identifierValue) {
     this.identifierValue = identifierValue;
  }

  public String getIdentifierValue() {
     return this.identifierValue ;
  }
}

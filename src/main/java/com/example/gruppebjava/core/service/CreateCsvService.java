package com.example.gruppebjava.core.service;

public class CreateCsvService {

    /*
public void onClickCsvTeilnehmerliste(ActionEvent actionEvent) {
        if (hatKursTeilnehmer()) {
            hbxCsvTeilnehmerliste.setVisible(true);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Speichern unter");
            fileChooser.setInitialFileName(kvModel.aktuellerKurs.getName().replace(" ","_"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV-Datei (*.csv)", "*.csv"));
            File file = fileChooser.showSaveDialog(mainCtrl.mainStage);
            List<Person> pkListe = kvModel.getPkListe().getPersonAlsTeilnehmer(kvModel.aktuellerKurs);
            if (file != null) {
                try {
                    FileWriter writer = new FileWriter(file);
                    String csvTrenner = pkListe.get(0).getCSVTRENNER();
                    writer.append("Anrede" + csvTrenner +
                            "Titel" + csvTrenner +
                            "Vorname" + csvTrenner +
                            "Nachname" + csvTrenner +
                            "Strasse" + csvTrenner +
                            "PLZ" + csvTrenner +
                            "Ort" + csvTrenner +
                            "E-Mail" + csvTrenner +
                            "telefon" + csvTrenner +
                            '\n');
                    for (Person p : pkListe) {
                        writer.append(p.toCsv());
                        writer.append('\n');
                    }
                    writer.flush();
                    writer.close();
                    System.out.println("CSV-Datei wurde erfolgreich gespeichert.");
                } catch (Exception e) {
                    Meldung.eingabeFehler(("Fehler beim Speichern der CSV-Datei: " + e.getMessage()));
                }
            }
        }
    }
     */
}

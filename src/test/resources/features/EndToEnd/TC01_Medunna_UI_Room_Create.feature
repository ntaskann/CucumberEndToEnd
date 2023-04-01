@Medunna_Create_Room,@e2e
Feature: room create

  Scenario: TC01_Oda_Olusturma
    Given "medunnaURl" sayfasina gidilir
    When admin olarak giris yapilir
    When itemsTitles menudeki room sayfasina gidilir
    When create an new Room tiklanir
    When gecerli degerler girilerek room olusturulur
    Then room olusturuldugu dogrulanir
    And sayfa kapatilir







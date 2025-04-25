import java.util.ArrayList;

class Playlista {
    private String nazwa;
    private ArrayList<String> utwory;

    public Playlista(String nazwa) {
        this.nazwa = nazwa;
        this.utwory = new ArrayList<>();
    }

    public String getNazwa() {
        return nazwa;
    }

    public void dodajUtwor(String utwor) {
        if (!utwory.contains(utwor)) {
            utwory.add(utwor);
        }
    }

    public void usunUtwor(String utwor) {
        utwory.remove(utwor);
    }

    public void wyswietlUtwory() {
        System.out.println("Playlista: " + nazwa);
        for (String utwor : utwory) {
            System.out.println("- " + utwor);
        }
    }

    public boolean zawieraUtwor(String utwor) {
        return utwory.contains(utwor);
    }
}

public class BibliotekaMuzyczna {
    private ArrayList<String> utwory;
    private ArrayList<Playlista> playlisty;

    public BibliotekaMuzyczna() {
        this.utwory = new ArrayList<>();
        this.playlisty = new ArrayList<>();
    }

    // Operacje na utworach
    public void dodajUtwor(String utwor) {
        if (!utwory.contains(utwor)) {
            utwory.add(utwor);
        } else {
            System.out.println("Utwór już istnieje: " + utwor);
        }
    }

    public void usunUtwor(String utwor) {
        if (utwory.remove(utwor)) {
            for (Playlista p : playlisty) {
                p.usunUtwor(utwor);
            }
            System.out.println("Utwór usunięty: " + utwor);
        } else {
            System.out.println("Nie znaleziono utworu: " + utwor);
        }
    }

    public void wyswietlUtwory() {
        System.out.println("Wszystkie utwory:");
        for (String utwor : utwory) {
            System.out.println("- " + utwor);
        }
    }

    public void wyszukajUtwory(String fraza) {
        System.out.println("Wyniki wyszukiwania dla: " + fraza);
        utwory.stream()
                .filter(utwor -> utwor.toLowerCase().contains(fraza.toLowerCase()))
                .forEach(System.out::println);
    }

    // Operacje na playlistach
    public void utworzPlayliste(String nazwa) {
        if (znajdzPlayliste(nazwa) == null) {
            playlisty.add(new Playlista(nazwa));
            System.out.println("Utworzono playlistę: " + nazwa);
        } else {
            System.out.println("Playlista już istnieje: " + nazwa);
        }
    }

    public Playlista znajdzPlayliste(String nazwa) {
        for (Playlista p : playlisty) {
            if (p.getNazwa().equalsIgnoreCase(nazwa)) {
                return p;
            }
        }
        return null;
    }

    public void dodajUtworDoPlaylisty(String nazwaPlaylisty, String utwor) {
        Playlista playlista = znajdzPlayliste(nazwaPlaylisty);
        if (playlista != null) {
            if (utwory.contains(utwor)) {
                playlista.dodajUtwor(utwor);
                System.out.println("Dodano utwór do playlisty.");
            } else {
                System.out.println("Nie ma takiego utworu w bibliotece.");
            }
        } else {
            System.out.println("Nie znaleziono playlisty.");
        }
    }

    public void wyswietlPlayliste(String nazwa) {
        Playlista playlista = znajdzPlayliste(nazwa);
        if (playlista != null) {
            playlista.wyswietlUtwory();
        } else {
            System.out.println("Nie znaleziono playlisty.");
        }
    }

    public void wyswietlWszystkiePlaylisty() {
        System.out.println("Wszystkie playlisty:");
        for (Playlista p : playlisty) {
            System.out.println("- " + p.getNazwa());
        }
    }

    // Przykład użycia
    public static void main(String[] args) {
        BibliotekaMuzyczna biblioteka = new BibliotekaMuzyczna();

        // Dodawanie utworów
        biblioteka.dodajUtwor("Imagine - John Lennon");
        biblioteka.dodajUtwor("Bohemian Rhapsody - Queen");
        biblioteka.dodajUtwor("Smells Like Teen Spirit - Nirvana");

        // Wyświetlanie utworów
        biblioteka.wyswietlUtwory();

        // Tworzenie playlist
        biblioteka.utworzPlayliste("Rock Classics");
        biblioteka.utworzPlayliste("Chill Vibes");

        // Dodawanie utworów do playlist
        biblioteka.dodajUtworDoPlaylisty("Rock Classics", "Bohemian Rhapsody - Queen");
        biblioteka.dodajUtworDoPlaylisty("Rock Classics", "Smells Like Teen Spirit - Nirvana");

        // Wyświetlanie jednej playlisty
        biblioteka.wyswietlPlayliste("Rock Classics");

        // Wyszukiwanie
        biblioteka.wyszukajUtwory("queen");

        // Usuwanie utworu
        biblioteka.usunUtwor("Smells Like Teen Spirit - Nirvana");

        // Wyświetlenie playlisty po usunięciu utworu
        biblioteka.wyswietlPlayliste("Rock Classics");
    }
}

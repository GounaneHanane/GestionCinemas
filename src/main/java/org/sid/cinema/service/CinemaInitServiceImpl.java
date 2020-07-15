package org.sid.cinema.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.sid.cinema.dao.*;
import org.sid.cinema.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService {
	@Autowired
	private VilleRepository villeRepository;
	@Autowired
	private CinemaRepository cinemaRepository;
	@Autowired
	private SalleRepository salleRepository;
	@Autowired
	private PlaceRepository placeRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private SeanceRepository seanceRepository;
	@Autowired
	private ProjectionRepository projectionRepository;
	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public void initVilles() {
		Stream.of("Casablanca", "Marrakesh", "Rabat", "Tanger", "Agadir").forEach(name -> {
			Ville ville = new Ville();
			ville.setName(name);
			villeRepository.save(ville);
		});

	}

	@Override
	public void initCinemas() {
		villeRepository.findAll().forEach(v -> {
			Stream.of("Megarama", "IMax", "Founoun", "Chahrazad").forEach(c -> {
				Cinema cinema = new Cinema();
				cinema.setName(c);
				cinema.setVille(v);
				cinema.setNombreSalles((int) (3 + Math.random() * 7));
				cinemaRepository.save(cinema);
			});
		});

	}

	@Override
	public void initSalles() {
		cinemaRepository.findAll().forEach((c -> {
			for (int i = 0; i < c.getNombreSalles(); i++) {
				Salle salle = new Salle();
				salle.setCinema(c);
				salle.setNombrePlace((int) (15 + Math.random() * 7));
				salle.setName("Salle " + (i + 1));
				salleRepository.save(salle);
			}
		}));

	}

	@Override
	public void initSeances() {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		Stream.of("12:00", "15:00", "17:00", "19:00", "21:00").forEach(s -> {
			Seance seance = new Seance();
			try {
				seance.setHeureDepart(dateFormat.parse(s));
				seanceRepository.save(seance);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});
	}

	@Override
	public void initPlaces() {
		salleRepository.findAll().forEach(s -> {
			for (int i = 0; i < s.getNombrePlace(); i++) {
				Place place = new Place();
				place.setNumero(i + 1);
				place.setSalle(s);
				placeRepository.save(place);

			}
		});
	}

	@Override
	public void initCategories() {
		Stream.of("Drama", "Comédie", "Romance", "Tragédie").forEach(c -> {
			Categorie categorie = new Categorie();
			categorie.setName(c);
			categorieRepository.save(categorie);
		});
	}

	@Override
	public void initFilms() {
		double[] durees = new double[] { 1, 1.2, 2, 2.5, 3 };
		List<Categorie> categories = categorieRepository.findAll();
		Stream.of("The 100", "Lost in space", "The hunger games", "Game of thrones", "Cat Women").forEach(f -> {
			Film film = new Film();
			film.setTitre(f);
			film.setDuree(durees[new Random().nextInt(durees.length)]);
			film.setPhoto(f.replaceAll(" ", "")+".jpg");
			film.setCategorie(categories.get(new Random().nextInt(categories.size())));
			filmRepository.save(film);

		});

	}

	@Override
	public void initProjections() {
		double[] prices = new double[] { 30, 50, 60, 70, 90, 100 };
		List<Film> films=filmRepository.findAll();
		villeRepository.findAll().forEach(v -> {
			v.getCinemas().forEach(c -> {
				c.getSalles().forEach(s -> {
					int index=new Random().nextInt(films.size());
					//filmRepository.findAll().forEach(f -> {
					Film f=films .get(index);
						seanceRepository.findAll().forEach(sean -> {
							Projection projection = new Projection();
							projection.setFilm(f);
							projection.setSalle(s);
							projection.setSeance(sean);
							projection.setDateProjection(new Date());
							projection.setPrix(prices[new Random().nextInt(prices.length)]);
							projectionRepository.save(projection);
						});
					//});

				});
			});
		});
	}

	@Override
	public void initTickets() {
		projectionRepository.findAll().forEach(p -> {
			p.getSalle().getPlaces().forEach(place -> {
				Ticket ticket=new Ticket();
				ticket.setPlace(place);
				ticket.setPrix(p.getPrix());
				ticket.setProjection(p);
				ticket.setReservee(false);
				ticketRepository.save(ticket);
			});
		});

	}

}

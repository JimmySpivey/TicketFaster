package tf.services;

import tf.seats.Reservation;
import tf.seats.SeatHold;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Jimmy Spivey
 */
public class ReservationService {

    private RandomStringService fiveRandomChars;
    private Map<Integer, Reservation> reservationsByHoldId = new HashMap<>();
    private Set<Reservation> reservations = new HashSet<>();

    public ReservationService(RandomStringService fiveRandomChars) {
        this.fiveRandomChars = fiveRandomChars;
    }

    public String reserve(SeatHold hold) {
        String code;
        do {
            code = fiveRandomChars.nextString();
        } while (reservations.contains(code));
        Reservation reservation = new Reservation(code, hold);
        reservations.add(reservation);
        reservationsByHoldId.put(hold.getId(), reservation);
        return code;
    }

    public boolean isReserved(SeatHold hold) {
        return reservationsByHoldId.keySet().contains(hold.getId());
    }

}
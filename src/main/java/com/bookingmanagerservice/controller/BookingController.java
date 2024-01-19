package com.bookingmanagerservice.controller;

private final BookingService bookingService;
@RestController
@RequestMapping("/bookings")

@Autowired
public BookingController(BookingService bookingService) {
    this.bookingService = bookingService;
}

// Obter todas as reservas
@GetMapping
public List<Booking> getAllBookings() {
    return bookingService.getAllBookings();
}

// Criar uma nova reserva
@PostMapping
public Booking addBooking(@RequestBody Booking booking) {
    return bookingService.addBooking(booking);
}

// Atualizar uma reserva existente
@PutMapping("/{id}")
public Booking updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
    return bookingService.updateBooking(id, booking);
}

// Deletar uma reserva
@DeleteMapping("/{id}")
public void deleteBooking(@PathVariable Long id) {
    bookingService.deleteBooking(id);
}

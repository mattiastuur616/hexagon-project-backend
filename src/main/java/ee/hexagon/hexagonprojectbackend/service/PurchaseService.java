package ee.hexagon.hexagonprojectbackend.service;

import ee.hexagon.hexagonprojectbackend.mapper.PurchaseMapper;
import ee.hexagon.hexagonprojectbackend.model.ClientUser;
import ee.hexagon.hexagonprojectbackend.model.Purchase;
import ee.hexagon.hexagonprojectbackend.repository.ClientUserRepository;
import ee.hexagon.hexagonprojectbackend.repository.PurchaseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final ClientUserRepository clientUserRepository;
    private final PurchaseRepository purchaseRepository;

    @Transactional
    public String addPurchase(String email, Long chapter) {
        Optional<ClientUser> existingClient = clientUserRepository.findClientUserByEmailIgnoreCase(email);
        if (existingClient.isEmpty()) {
            return "Book wasn't added";
        }
        Purchase purchase = new Purchase();
        purchase.setClientUser(existingClient.get());
        purchase.setChapter(chapter);
        purchase.setPageNumber(0L);
        purchaseRepository.save(purchase);
        return "Book was added to user library";
    }

    @Transactional
    public boolean isPurchased(String email, Long chapter) {
        Optional<ClientUser> existingClient = clientUserRepository.findClientUserByEmailIgnoreCase(email);
        if (existingClient.isEmpty()) {
            return false;
        }
        Optional<Purchase> existingPurchase = purchaseRepository.findByClientUserAndChapter(existingClient.get(), chapter);
        return existingPurchase.isPresent();
    }

    @Transactional
    public Long getPageNumber(String email, Long chapter) {
        Optional<ClientUser> existingClient = clientUserRepository.findClientUserByEmailIgnoreCase(email);
        if (existingClient.isEmpty()) {
            return null;
        }
        Optional<Purchase> existingPurchase = purchaseRepository.findByClientUserAndChapter(existingClient.get(), chapter);
        return existingPurchase.map(Purchase::getPageNumber).orElse(null);
    }

    @Transactional
    public String saveBookMark(String email, Long chapter, Long bookMark) {
        Optional<ClientUser> existingClient = clientUserRepository.findClientUserByEmailIgnoreCase(email);
        if (existingClient.isEmpty()) {
            return "No bookmark was saved";
        }
        Optional<Purchase> existingPurchase = purchaseRepository.findByClientUserAndChapter(existingClient.get(), chapter);
        if (existingPurchase.isEmpty()) {
            return "No bookmark was saved";
        }
        Purchase purchase = existingPurchase.get();
        purchase.setPageNumber(bookMark);
        purchaseRepository.save(purchase);
        return "Bookmark was saved";
    }

    @Transactional
    public List<String> getAllBooksBoughtByTheUser(String email) throws Exception {
        List<String> chapterNumbers = new ArrayList<>();
        Optional<ClientUser> existingUser = clientUserRepository.findClientUserByEmailIgnoreCase(email);
        if (existingUser.isEmpty()) {
            throw new Exception("No user was found");
        }
        ClientUser realUser = existingUser.get();
        List<Purchase> booksInOrder = purchaseRepository.findByClientUser(realUser).stream().sorted(Comparator.comparing(Purchase::getChapter)).toList();
        for (Purchase purchase : booksInOrder) {
            chapterNumbers.add(String.valueOf(purchase.getChapter()));
        }
        return chapterNumbers;
    }
}

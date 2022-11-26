package com.example.eazyschool.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.eazyschool.constants.EazySchoolConstants;
import com.example.eazyschool.model.Contact;
import com.example.eazyschool.repository.ContactRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
//@RequestScope
//@SessionScope
//@ApplicationScope
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	
	public ContactService() {
		System.out.println("Contact Service bean created");
		
	}
	
	public boolean saveMessageDetails(Contact contact) {
		boolean isSaved = false;
		contact.setStatus(EazySchoolConstants.OPEN);
		//contact.setCreatedBy(EazySchoolConstants.ANONYMOUS);
		//contact.setCreatedAt(LocalDateTime.now());
		log.info(contact.toString());
		//int result = contactRepository.saveContactMsg(contact);
		Contact savedContact = contactRepository.save(contact);
		if (savedContact!=null && savedContact.getContactId()>0) {
			isSaved=true;
		}
		return isSaved;
	}
	
	public Page<Contact> findMsgsWithOpenStatus(int pageNum,String sortField, String sortDir){
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findByStatus(
                EazySchoolConstants.OPEN,pageable);
        return msgPage;
    }
	/*
	public List<Contact> findMsgsWithOpenStatus(){
		
		List<Contact> contactMsgs= contactRepository.findByStatus(EazySchoolConstants.OPEN);
		return contactMsgs;
	}*/
	
	public boolean updateMsgStatus(int contactID) {
		boolean isUpdated = false;
		Optional<Contact> contact= contactRepository.findById(contactID);
		contact.ifPresent(contactL ->{
			contactL.setStatus(EazySchoolConstants.CLOSE);
			//contactL.setUpdatedBy(updatedBy);
			//contactL.setUpdatedAt(LocalDateTime.now());
			
		});
		Contact savedContact = contactRepository.save(contact.get());
		if (savedContact !=null && savedContact.getUpdatedBy()!=null) {
			isUpdated=true;
		}
		return isUpdated;
	}


}

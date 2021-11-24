package com.yanolja.service;

import com.yanolja.configuration.DefaultException;
import com.yanolja.configuration.ResponseMessage;
import com.yanolja.configuration.StatusCode;
import com.yanolja.domain.Owner;
import com.yanolja.repository.owner.OwnerRepository;
import com.yanolja.utils.AES128;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OwnerService {
    private String owner_secret_key = "1234567890asdfgh";
    @Autowired
    private OwnerRepository ownerRepository;
    public Owner.RegisterRes register(Owner.RegisterReq owner) throws DefaultException {
        //TODO 아이디 유효성 검사
        /*

         */

        // 암호화(AES128) key : owner_secret_key
        try { // 암호화 예외처리
            AES128 aes128 = new AES128(owner_secret_key);
            String pwd = aes128.encrypt(owner.getPassword());
            owner.setPassword(pwd);
        }catch(Exception encryptError){
            throw new DefaultException(StatusCode.ENCRYPT_ERROR, ResponseMessage.ENCRYPT_ERROR);
        }
        return ownerRepository.insert(owner);
    }
    public Owner.LoginRes login(Owner.LoginReq owner) throws DefaultException {
        String email = owner.getEmail();
        // 유저 아이디로 유저 찾기
        Owner.Info realOwner = ownerRepository.findByEmail(email);
        //  아이디 이미 존재하면 예외 처리
        if(realOwner == null){
            throw new DefaultException(StatusCode.LOGIN_FAIL, ResponseMessage.NOT_FOUND_USER);
        }
        // 복호화(AES128) key : neo
        String pwd;
        try { // 복호화 예외처리
            AES128 aes128 = new AES128(owner_secret_key);
            pwd = aes128.decrypt(realOwner.getPassword());
        }catch(Exception decryptError){
            throw new DefaultException(StatusCode.DECRYPT_ERROR, ResponseMessage.DECRYPT_ERROR);
        }
        // 비밀번호 맞는지 비교
        if (owner.getPassword().equals(pwd)) {
            return Owner.LoginRes.builder().ownerId(realOwner.getOwnerId()).build();
        } else {
            throw new DefaultException(StatusCode.LOGIN_FAIL, ResponseMessage.LOGIN_FAIL);
        }
    }
    public Integer updateNickName(Owner.PatchReq owner) throws DefaultException{
        log.debug("owner Id = {}", owner.getOwnerId());
        int result = ownerRepository.updateById(owner);
        if(result == 0){ // 0이면 에러가 발생
            throw new DefaultException(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }else{
            return result;
        }
    }
    public Integer deleteById(Integer id) throws DefaultException{
        log.debug("owner id = {}", id);
        int result = ownerRepository.deleteById(id);
        if(result == 0){
            throw new DefaultException(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }else{
            return result;
        }
    }
}

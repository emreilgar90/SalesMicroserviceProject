package com.emre.manager;

import com.emre.dto.request.UserProfileSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(name="user-profile-elasticsearch",
url="{myapplication.feignclient.elasticsearch}",decode404=true)
public interface IUserProfileElasticService {

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody UserProfileSaveRequestDto dto);
}

package ru.strelchm.raiffeisenchallenge.api;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.digital_mind.marketplace.domain.mongo.Tag;
import ru.digital_mind.marketplace.dto.StringDto;
import ru.digital_mind.marketplace.dto.UserContext;
import ru.digital_mind.marketplace.exception.NotFoundException;
import ru.digital_mind.marketplace.service.UserService;
import ru.digital_mind.marketplace.service.impl.TagService;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Api("REST controller 4 Tags operations")
@RequestMapping("/api/socks")
@Validated
//@PreAuthorize("hasAnyRole()") todo - держать открытой регистрацию
public class TagController extends ParentController {
    private final SockService tagService;

    @Autowired
    public TagController(TagService TagService) {
        this.tagService = TagService;
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.getAll();
    }

    @PostMapping
    public Tag createTag(@NotNull(message = NULL_ID_REQUEST_EXCEPTION) @Validated @RequestBody StringDto stringDto) {
        return tagService.createTag(new Tag(stringDto.getName()));
    }

    @GetMapping("/{id}")
    public Tag getTagById(@NotNull(message = NULL_ID_REQUEST_EXCEPTION) @Validated @PathVariable String id) {
        return tagService.getById(id).orElseThrow(() -> new NotFoundException("Tag with " + id + " not found"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTag(@NotNull(message = NULL_ID_REQUEST_EXCEPTION) @Validated @PathVariable String id,
                           @ModelAttribute(USER_CONTEXT) UserContext userContext) {
        tagService.deleteTag(id);
    }
}

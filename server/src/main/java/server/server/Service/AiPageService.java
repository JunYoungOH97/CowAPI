package server.server.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.server.Domain.Dto.AiDto;
import server.server.Domain.Dto.AiListDto;
import server.server.Domain.Dto.QnaDto;
import server.server.Domain.Entity.Ai;
import server.server.Domain.Entity.Qna;
import server.server.Domain.Repository.AiPageRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AiPageService {
    private final AiPageRepository aiPageRepository;

    public AiDto aiOnePage(AiDto aiDto) {
        return AiDto.of(aiPageRepository.findByName(aiDto.getName()).orElseThrow());
    }

    public AiListDto aiListPage() {
        List<Ai> aiList = aiPageRepository.findAiByExist().orElseThrow();

        List<AiDto> aiDtoList = new ArrayList<>();
        for (Ai ai : aiList) {
            aiDtoList.add(AiDto.of(ai));
        }
        return AiListDto.builder().aiDtoList(aiDtoList).build();
    }

}

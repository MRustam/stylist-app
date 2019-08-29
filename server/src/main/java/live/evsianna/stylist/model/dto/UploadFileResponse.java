package live.evsianna.stylist.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Rustam Mamedov
 */

@Getter
@Setter
public class UploadFileResponse {

    private String fileName;

    private String fileDownloadUri;

    private String fileType;

    private long size;

    @Builder
    public UploadFileResponse(String fileName, String fileDownloadUri,
                              String fileType, long size) {

        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

}

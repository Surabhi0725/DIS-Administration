package sgsits.cse.dis.administration.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Upload file response.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileResponse {

  /**
   * The File name.
   */
  private String fileName;
  /**
   * The File download uri.
   */
  private String fileDownloadUri;
  /**
   * The File type.
   */
  private String fileType;
  /**
   * The Size.
   */
  private long size;
}

package com.kh.myprj.domain.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 업로드한 파일의 메타정보
 * @author mypc
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaOfUploadFile {
  private String store_fname; 	
  private String upload_fname; 	
  private String fsize;					
  private String ftype;					
}

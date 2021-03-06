package cn.skill6.website.dao.impl.store;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.skill6.common.entity.po.store.StoreFile;
import cn.skill6.common.exception.general.NullPointerException;
import cn.skill6.website.dao.intf.store.StoreFileDao;
import cn.skill6.website.dao.mappers.store.StoreFileMapper;

/**
 * 文件存储操作实现类
 *
 * @author 何明胜
 * @version 1.6
 * @since 2018年8月28日 上午12:18:06
 */
@Repository
public class StoreFileDaoOper implements StoreFileDao {
  private static final Logger logger = LoggerFactory.getLogger(StoreFileDaoOper.class);

  @Autowired private StoreFileMapper storeFileMapper;

  @Override
  public int deleteByFileId(Long fileId) {
    logger.warn("删除id为{}的文件", fileId);
    return storeFileMapper.deleteByPrimaryKey(fileId);
  }

  @Override
  public Long addFileDownload(StoreFile storeFile) {
    if (storeFile == null || storeFile.getFileId() == null) {
      throw new NullPointerException("内容或者id为空");
    }

    storeFile.setFileUploadTime(new Date());
    storeFile.setFileDownloadCount(0);

    storeFileMapper.insert(storeFile);
    logger.info("增加文件信息成功,{}", storeFile);

    return storeFile.getFileId();
  }

  @Override
  public StoreFile findByFileId(Long fileId) {
    StoreFile storeFile = storeFileMapper.selectByPrimaryKey(fileId);
    logger.info("找到id为{}的文件下载信息,{}", fileId, storeFile);

    return storeFile;
  }

  @Override
  public List<StoreFile> findAll() {
    List<StoreFile> storeFiles = storeFileMapper.selectAll();
    logger.info("找到所有文件存储记录,{}", storeFiles);

    return storeFiles;
  }

  @Override
  public void modifyByFileId(StoreFile storeFile) {
    if (storeFile.getFileId() == null) {
      throw new NullPointerException("文件存储id不能为null");
    }

    storeFileMapper.updateByPrimaryKey(storeFile);
    logger.info("成功修改id为{}的文件存储记录,{}", storeFile.getFileId(), storeFile);
  }
}

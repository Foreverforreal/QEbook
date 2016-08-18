package com.feifanuniv.collegeebook.mapper;

import java.util.List;

import com.feifanuniv.collegeebook.entity.Ebook;

 public interface EbookMapper {
	 List<Ebook> selectAllEbook();
	 List<Ebook> selectEbookByBookName(String EbookName);
	 List<Ebook> selectEbookBySSId(List<String> SSId);
	 List<Ebook> selectEbookById(int id);
	 List<Ebook> selectEbookByAllFields(Ebook ebook);
	 int deleteEbook(int id);
	 int insertEbook(Ebook college);
	 int updateEbook(Ebook college);
}

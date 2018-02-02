
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import repositories.EducationRecordRepository;
import domain.EducationRecord;

public class StringToEducationRecordConverter implements Converter<String, EducationRecord> {

	@Autowired
	EducationRecordRepository	educationRecordRepository;


	@Override
	public EducationRecord convert(final String text) {
		EducationRecord result = null;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.educationRecordRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}

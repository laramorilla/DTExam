
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import repositories.EndorserRecordRepository;
import domain.EndorserRecord;

public class StringToEndorserRecordConverter implements Converter<String, EndorserRecord> {

	@Autowired
	EndorserRecordRepository	endorserRecordRepository;


	@Override
	public EndorserRecord convert(final String text) {
		EndorserRecord result = null;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.endorserRecordRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}

package telran.view;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.function.Function;
import java.util.function.Predicate;

public interface InputOutput {
	String readString(String prompt);

	void writeString(String str);

	default void writeLine(Object obj){
		writeString(obj.toString() + "\n");
	}

	default <T> T readObject(String prompt, String errorPrompt, Function<String, T> mapper) {
		boolean running = false;
		T res = null;
		do {
			running = false;
			try {
				String strRes = readString(prompt);
				res = mapper.apply(strRes);
			} catch (Exception e) {
				writeLine(errorPrompt + ": " + e.getMessage());
				running = true;
			}
			
		} while(running);
		return res;
	}
	/**
	 * 
	 * @param prompt
	 * @param errorPrompt
	 * @return Integer number
	 */
	default Integer readInt(String prompt, String errorPrompt) {
		return readObject(prompt, errorPrompt, Integer::parseInt);
	}

	default Long readLong(String prompt, String errorPrompt) {
		return readObject(prompt, errorPrompt, string ->{
			try{
				return Long.parseLong(string);
			} catch(Exception e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		});
	}

	default Double readDouble(String prompt, String errorPrompt) {
		return readObject(prompt, errorPrompt, string ->{
			try{
				return Double.parseDouble(string);
			} catch(Exception e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		});
	}

	default Double readNumberRange(String prompt, String errorPrompt, double min, double max) {
		return readObject(prompt, errorPrompt, string ->{
			Double res = Double.parseDouble(string);
			if(res < min || res > max) {
				throw new IllegalArgumentException("The number should be in range between" + min + "and" + max);
			}
			return res;
		});
	}

	default String readStringPredicate(String prompt, String errorPrompt, Predicate<String> predicate) {
		return readObject(prompt, errorPrompt, string ->{
			try{
				if(!predicate.test(string)){
					throw new IllegalArgumentException("The entered data doesn't in correct format");
				}
				return string;
			} catch(Exception e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		});
	}
	default String readStringOptions(String prompt, String errorPrompt,	HashSet<String> options){
		return readObject(prompt, errorPrompt, string ->{
			try{
				if(!options.contains(string)) {
					throw new IllegalArgumentException("The entered data doesn't in correct format");
				}
				return string;
			} catch (Exception e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		});
	}
	default LocalDate readIsoDate(String prompt, String errorPrompt){
		return readObject(prompt, errorPrompt, string ->{
			try {
				return LocalDate.parse(string);
			} catch(Exception e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		});
	}
	default LocalDate readIsoDateRange(String prompt, String errorPrompt, LocalDate from, LocalDate to) {
		return readObject(prompt, errorPrompt, string -> {
			try {
				LocalDate res = LocalDate.parse(string);
				if(res.isBefore(from) || res.isAfter(to)) {
					throw new IllegalArgumentException("The data should be in range between" + from + "and" + to);
				}
				return res;
			} catch(Exception e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		});
	}
	

}

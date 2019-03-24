package com.orhanobut.hawk;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;

import java.util.List;
import java.util.Set;

final class SharedPreferencesStorage implements Storage {

  private final SharedPreferences preferences;

  SharedPreferencesStorage(Context context, String tag) {
    preferences = context.getSharedPreferences(tag, Context.MODE_PRIVATE);
  }

  SharedPreferencesStorage(SharedPreferences preferences) {
    this.preferences = preferences;
  }

  @Override public <T> boolean put(String key, T value) {
    HawkUtils.checkNull("key", key);
    return getEditor().putString(key, String.valueOf(value)).commit();
  }

  @SuppressWarnings("unchecked")
  @Override public <T> T get(String key) {
    return (T) preferences.getString(key, null);
  }

  @Override public boolean delete(String key) {
    return getEditor().remove(key).commit();
  }

  @Override public boolean contains(String key) {
    return preferences.contains(key);
  }

  @Override public boolean deleteAll() {
    return getEditor().clear().commit();
  }

  @Override public long count() {
    return preferences.getAll().size();
  }

  @Override @NonNull public Set<String> keys() {
    return preferences.getAll().keySet();
  }

  private SharedPreferences.Editor getEditor() {
    return preferences.edit();
  }

}

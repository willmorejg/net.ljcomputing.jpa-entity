/**
           Copyright 2016, James G. Willmore

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package net.ljcomputing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.ljcomputing.entity.PersistedEntity;
import net.ljcomputing.repository.BaseCrudRepository;

/**
 * Abstract implementation of an entity service.
 *
 * @author James G. Willmore
 * @param <T> the persisted entity
 * @param <R> the repository for the persisted entity
 */
public abstract class AbstractEntityService<T extends PersistedEntity, R extends BaseCrudRepository<T>> implements EntityService<T> {
  
  /** The repository. */
  @Autowired
  protected R repository;

  /**
   * @see net.ljcomputing.service.EntityService#create(net.ljcomputing.entity.PersistedEntity)
   */
  @Override
  public T create(T entity) {
    return repository.save(entity);
  }

  /**
   * @see net.ljcomputing.service.EntityService#readAll()
   */
  @Override
  public List<T> readAll() {
    return (List<T>) repository.findAll();
  }

  /**
   * @see net.ljcomputing.service.EntityService#readById(java.lang.Long)
   */
  @Override
  public T readById(Long id) {
    return repository.findOne(id);
  }

  /**
   * @see net.ljcomputing.service.EntityService#update(net.ljcomputing.entity.PersistedEntity)
   */
  @Override
  public T update(T entity) {
    return repository.save(entity);
  }

  /**
   * @see net.ljcomputing.service.EntityService#delete(net.ljcomputing.entity.PersistedEntity)
   */
  @Override
  public void delete(T entity) {
    repository.delete(entity);
  }

}

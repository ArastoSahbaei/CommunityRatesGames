
package com.tobenamed.platform;

import org.springframework.data.repository.*;
import org.springframework.stereotype.*;

// Conflicts with org.springframework.data.repository.Repository, so be explicit.
@org.springframework.stereotype.Repository
public interface PlatformRepository extends CrudRepository<Platform, Integer> {
}

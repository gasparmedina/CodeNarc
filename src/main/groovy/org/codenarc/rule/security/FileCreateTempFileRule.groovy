/*
 * Copyright 2011 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codenarc.rule.security

import org.codenarc.rule.AbstractAstVisitor
import org.codenarc.rule.AbstractAstVisitorRule
import org.codehaus.groovy.ast.expr.MethodCallExpression
import org.codenarc.util.AstUtil

/**
 * The File.createTempFile() method is insecure, and has been deprecated by the ESAPI secure coding library.
 * It has been replaced by the ESAPI Randomizer.getRandomFilename(String) method.
 *
 * @author Hamlet D'Arcy
 * @version $Revision: 24 $ - $Date: 2009-01-31 13:47:09 +0100 (Sat, 31 Jan 2009) $
 */
class FileCreateTempFileRule extends AbstractAstVisitorRule {
    String name = 'FileCreateTempFile'
    int priority = 2
    Class astVisitorClass = FileCreateTempFileAstVisitor
    String doNotApplyToFilesMatching = DEFAULT_TEST_CLASS_NAMES
}

class FileCreateTempFileAstVisitor extends AbstractAstVisitor {
    @Override
    void visitMethodCallExpression(MethodCallExpression call) {

        if (AstUtil.isMethodCall(call, 'File', 'createTempFile', 2) ||
                AstUtil.isMethodCall(call, 'File', 'createTempFile', 3)) {
            addViolation(call, 'The method File.createTempFile is insecure. Use a secure API such as that provided by ESAPI')
        }
        super.visitMethodCallExpression(call)
    }


}
